package controle.entidade.item;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import controle.entidade.funcionariocontrole.FuncionarioControle;
import modelo.dao.item.ItemDAO;
import modelo.dao.produto.ProdutoDAO;
import modelo.entidade.item.Item;
import modelo.entidade.produto.Produto;
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

				adicionar();
				atualizarTabela();
			}
		});

		telaVenda.getBtnLimparCarrinho().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				excluirTudo();
				atualizarTabela();
			}
		});

		telaVenda.getBtnRemoverProduto().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				excluir();
				atualizarTabela();
			}
		});

	}

	protected void adicionar() {

		int quantidade = Integer.parseInt(telaVenda.getTxtQuantidade());
		int idProduto = Integer.parseInt(telaVenda.getTxtCodigo());
		Produto produto = produtoDAO.getId(idProduto);

		if (produto == null) {
			JOptionPane.showMessageDialog(telaVenda, "Produto não encontrado no sistema.", "Erro",
					JOptionPane.ERROR_MESSAGE);
			return;
		}

		if (quantidade <= 0) {
			JOptionPane.showMessageDialog(telaVenda, "Quantidade deve ser maior que zero.", "Erro",
					JOptionPane.ERROR_MESSAGE);
			return;
		}

		Item item = new Item();
		item.setQuantidade(quantidade);
		item.setProduto(produto);
		item.setId(itemDAO.getId());
		System.out.println("ID CERTO:"+item.getId());

		boolean sucesso;
		try {
			sucesso = itemDAO.cadastrarItem(item, funcionarioControle.getCpfUsuarioLogado());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			sucesso = false;
		}

		if (sucesso) {
			JOptionPane.showMessageDialog(telaVenda, "Item cadastrado com sucesso!");
		} else {
			JOptionPane.showMessageDialog(telaVenda, "Erro ao cadastrar o Item.", "Erro", JOptionPane.ERROR_MESSAGE);
		}
	}

	public void atualizarTabela() {
		
		
		
		List<Item> itens = itemDAO.listarItens();

		DefaultTableModel tableModel;
		tableModel = new DefaultTableModel();
		tableModel.addColumn("Código");
		tableModel.addColumn("Nome");
		tableModel.addColumn("Quantidade");
		tableModel.addColumn("Valor Total");

		for (Item item : itens) {
			Produto produto = item.getProduto();
			double valorTotal = item.getQuantidade() * produto.getValor();

			System.out.println("Número do item: "+item.getId());
			tableModel.addRow(new Object[] { item.getId(), produto.getNomeProduto(), item.getQuantidade(),
					String.format("%.2f", valorTotal) });
		}

		telaVenda.getTable().setModel(tableModel);
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
			// Pega o ID do item da tabela (assumindo que o ID do item está na primeira
			// coluna)
			int idItem = (Integer) telaVenda.getTable().getValueAt(selectedRow, 0);
			int resposta = JOptionPane.showConfirmDialog(telaVenda,
					"Você tem certeza que deseja excluir o item com ID: " + idItem + "?", "Confirmar Exclusão",
					JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
//funciona ate aq
			if (resposta == JOptionPane.YES_OPTION) {

				// Chama o método excluirItem do itemDAO para excluir o item do banco
				boolean excluido = itemDAO.excluirItem(idItem);

				if (excluido) {
					JOptionPane.showMessageDialog(telaVenda, "Item excluído com sucesso!");
					atualizarTabela(); // Atualiza a tabela após a exclusão
				} else {
					JOptionPane.showMessageDialog(telaVenda, "Erro ao excluir o Item.", "Erro",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		} else {
			// Se nenhuma linha for selecionada
			JOptionPane.showMessageDialog(telaVenda, "Selecione um Item para excluir.", "Erro",
					JOptionPane.WARNING_MESSAGE);
		}

	}
}