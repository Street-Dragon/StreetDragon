package controle.entidade.item;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import controle.entidade.funcionariocontrole.FuncionarioControle;
import modelo.dao.item.ItemDAO;
import modelo.dao.produto.ProdutoDAO;
import modelo.entidade.item.Item;
import modelo.entidade.produto.Produto;
import visao.TelaMensagens;
import visao.TelaVenda;

public class ItemController {
	private TelaVenda telaVenda;
	private ItemDAO itemDAO = new ItemDAO();
	private ProdutoDAO produtoDAO = new ProdutoDAO();
	private FuncionarioControle funcionarioControle = new FuncionarioControle();

	public void setTelaVenda(TelaVenda telaVenda) {
		this.telaVenda = telaVenda;
		atualizarTabela();

		telaVenda.getBtnAdicionarProduto().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				if (!telaVenda.setTxtCodigo().getText().isEmpty() && !telaVenda.setTxtQuantidade().getText().isEmpty()) {
					adicionar();
					atualizarTabela();
					limparCampos();
				} else {
					new TelaMensagens("Preencha todos os campos!", 1);
				}
			}
		});

		telaVenda.getBtnLimparCarrinho().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				excluirTudo();
				atualizarTabela();
				limparCampos();
			}
		});

		telaVenda.getBtnRemoverProduto().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				excluir();
				atualizarTabela();
				limparCampos();
			}
		});
		
		telaVenda.setTxtCodigo().addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {}
			@Override
			
			public void keyReleased(KeyEvent e) {
				if (telaVenda.getTxtCodigo().isBlank()) {
					telaVenda.setTxtValor().setText(null);
					telaVenda.setTxtNome().setText(null);
				} else {
					preecherCampos();
				}
			}
			
			@Override
			public void keyPressed(KeyEvent e) {}
		});
	}

	protected void adicionar() {

		int quantidade = Integer.parseInt(telaVenda.getTxtQuantidade());
		int idProduto = Integer.parseInt(telaVenda.getTxtCodigo());
		Produto produto = produtoDAO.getId(idProduto);

		if (produto == null) {
			new TelaMensagens("Produto não encontrado no sistema.", 1);
			return;
		}

		if (quantidade <= 0) {
			new TelaMensagens("Quantidade deve ser maior que zero.", 1);
			return;
		}

		Item item = new Item();
		item.setQuantidade(quantidade);
		item.setProduto(produto);
		item.setId(itemDAO.getId());

		boolean sucesso;
		try {
			sucesso = itemDAO.cadastrarItem(item, funcionarioControle.getCpfUsuarioLogado());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			sucesso = false;
		}

		if (sucesso) {
			new TelaMensagens("Item cadastrado com sucesso!", 0);
		} else {
			new TelaMensagens("Erro ao cadastrar o Item.", 1);
		}
	}

	public void atualizarTabela() {
		// atualizaTabela
		List<Item> itens = itemDAO.listarItens();

		DefaultTableModel tableModel;
		tableModel = new DefaultTableModel();
		tableModel.addColumn("Código");
		tableModel.addColumn("Nome");
		tableModel.addColumn("Quantidade");
		tableModel.addColumn("Valor Total");

		for (int i = 0; i < itens.size(); i++) {
			Item item = itens.get(i);
			Produto produto = item.getProduto();
			double valorTotal = item.getQuantidade() * produto.getValor();
			int indiceCorrespondente = i + 1;

			tableModel.addRow(new Object[] { indiceCorrespondente, produto.getNomeProduto(), item.getQuantidade(),
					String.format("%.2f", valorTotal) });

		}
		telaVenda.getTable().setModel(tableModel);
		atualizaTotal();
	}

	protected void excluirTudo() {
		System.out.println("Exclui tudo");
		try {
			itemDAO.excluirTodos();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		atualizarTabela();

	}

	protected void excluir() {
		int selectedRow = telaVenda.getTable().getSelectedRow(); // Pega a linha selecionada na tabela

		if (selectedRow != -1) {
			// Pega o ID do item da tabela
			int idItem = (Integer) telaVenda.getTable().getValueAt(selectedRow, 0);
			TelaMensagens tm = new TelaMensagens("Você tem certeza que deseja excluir o item com ID: " + idItem + "?");
			// funciona ate aq
			if (tm.getResposta()) {
				// Chama o método excluirItem do itemDAO para excluir o item do banco
				boolean excluido = itemDAO.excluirItem(idItem);

				if (excluido) {
					new TelaMensagens("Item excluído com sucesso!", 0);
					atualizarTabela(); // Atualiza a tabela após a exclusão
					limparCampos();
				} else {
					new TelaMensagens("Erro ao excluir Item.", 1);
				}
			}
		} else {
			// Se nenhuma linha for selecionada
			new TelaMensagens("Selecione um Item para excluir.", 3);
			
			
		}

	}

	private void atualizaTotal() {
		telaVenda.getLblTotal().setText("Total: R$ " + itemDAO.getTotal());
	}
	private void preecherCampos() {
		int codigo = Integer.parseInt(telaVenda.getTxtCodigo());
		Produto produto = produtoDAO.getId(codigo);
		if (produto != null) {
			telaVenda.setTxtNome().setText(produto.getNomeProduto());
			telaVenda.setTxtValor().setText(String.valueOf(produto.getValor()));
		}
	}
	private void limparCampos() {
		telaVenda.setTxtCodigo().setText(null);
		telaVenda.setTxtNome().setText(null);
		telaVenda.setTxtValor().setText(null);
		telaVenda.setTxtQuantidade().setText(null);
	}
}