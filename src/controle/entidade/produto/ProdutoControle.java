package controle.entidade.produto;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import modelo.dao.produto.ProdutoDAO;
import modelo.entidade.produto.Produto;
import visao.TelaCadastroProdutos;
import visao.TelaProdutos;

public class ProdutoControle {
	
	private TelaCadastroProdutos telaCadastroProduto;
	private TelaProdutos telaProdutos;
	private ProdutoDAO produtoDAO = new ProdutoDAO();

	public void setTelaProdutos(TelaProdutos telaProdutos) {
		this.telaProdutos = telaProdutos;
		
		System.out.println("entrou");
		telaProdutos.getBtnCadastrarProd().addActionListener(new ActionListener() {
			@Override
			
			public void actionPerformed(ActionEvent e) {
				System.out.println("botão clicado");
				// TelaCadastroProdutos telaCadastroProduto = new TelaCadastroProdutos();
				// telaCadastroProduto.setVisible(true);
			}
		});

		telaProdutos.getBtnDeletarProd().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("botão clicado");

			}
		});

		telaProdutos.getBtnEditProd().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("botão clicado");
			}
		});

	}
	
	public void setTelaCadastrarProduto(TelaCadastroProdutos telaCadastroProduto) {
		this.telaCadastroProduto = telaCadastroProduto;
	}
	
}

	/*
	 * 
	 * if (telaCadastroProduto == null) { System.out.println("botão ta cagado"); }
	 * else { telaCadastroProduto.setVisible(true); telaCadastroProduto.ClearText();
	 * telaCadastroProduto.getbtnCadastrarProduto().setText("Cadastrar Produto");
	 * telaCadastroProduto.setTitle("Cadastrar Produto");
	 * telaCadastroProduto.getTextFieldId().setText(String.valueOf(produtoDAO.Idshow()));
	 * } } });
	 * 
	 */
	
	
	/*

	public void setCadastroProduto(TelaCadastroProdutos telaCadastroProduto) {
		this.telaCadastroProduto = telaCadastroProduto;
		telaCadastroProduto.getbtnCadastrarProduto().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (telaCadastroProduto.getbtnCadastrarProduto().getText().equals("Cadastrar Produto")) {
					cadastrarProduto(telaCadastroProduto);
					telaCadastroProduto.ClearText();
					telaCadastroProduto.getTextFieldId().setText(String.valueOf(produtoDAO.Idshow()));
					listarProdutosTable();
				} else {

					Produto produto = new Produto();
					produto = produtoDAO.getId(Integer.valueOf(telaCadastroProduto.getTextFieldId().getText()));
					EditProduto(produto);
					listarProdutosTable();
					telaCadastroProduto.dispose();
				}
			}
		});
	}

	// ---------------------------------------Editar-------------------------------------
	public void setTelaEditarProduto(TelaProdutos telaProdutos) {
		this.telaProdutos = telaProdutos;
		telaProdutos.getBtnEditProd().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				JTable table = telaProdutos.getTable();
				int selectedRowIndex = table.getSelectedRow();
				if (selectedRowIndex == -1) {
					JOptionPane.showMessageDialog(telaProdutos, "Nenhum produto selecionado", "Erro",
							JOptionPane.ERROR_MESSAGE);
				} else {
					telaCadastroProduto.setVisible(true);
					telaCadastroProduto.getbtnCadastrarProduto().setText("Editar Produto");
					telaCadastroProduto.setTitle("Editar Produto");
					String firstColumnValue = table.getValueAt(selectedRowIndex, 0).toString();
					Produto produto = new Produto();
					produto = produtoDAO.getId(Integer.valueOf(firstColumnValue));
					telaCadastroProduto.setVisible(true);
					fillEdit(produto);
				}
			}
		});
	}
	// ---------------------------------------Deletar-------------------------------------

	public void setDeletarProduto(TelaProdutos telaProdutos) {
		telaProdutos.getBtnDeletarProd().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				DeletProduto();
				listarProdutosTable();
			}
		});
	}

	// ------------------------------------------------------------------------------------
	public void setCancelarProduto(TelaCadastroProdutos telaCadastroProduto) {
		this.telaCadastroProduto = telaCadastroProduto;
		telaCadastroProduto.getbtnCancelar().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				telaCadastroProduto.ClearText();
				telaCadastroProduto.getTextFieldId().setText(String.valueOf(produtoDAO.Idshow()));
			}
		});
	}

	public void listarProdutosTable() {
		List<Produto> produtos = produtoDAO.listarProdutos();
		DefaultTableModel tableModel = (DefaultTableModel) telaProdutos.getTable().getModel();
		tableModel.setRowCount(0);

		for (Produto produto : produtos) {
			tableModel.addRow(new Object[] { produto.getIdProduto(), produto.getNomeProduto(), produto.getValor(),
					produto.getQuantEstoque(), });
		}
	}

	public Produto getProdutoById(int id) {
		Produto produto = produtoDAO.getId(id);
		if (produto == null) {
			throw new IllegalStateException("Produt com ID " + id + "não encontrado.");
		}
		return produto;
	}

	public void EditProduto(Produto produto) {
		try {
			float valor = Float.parseFloat(telaCadastroProduto.getTextFieldValor());
			int estoque = Integer.parseInt(telaCadastroProduto.getTextFieldQntEstoque());
			String nome = telaCadastroProduto.getTextFieldNome();
			String material = telaCadastroProduto.getCbMaterial();
			String categoria = telaCadastroProduto.getCbCategoria();
			String variacao = telaCadastroProduto.getTextFieldVariacao();
			String tamanho = telaCadastroProduto.getCbTamnho();

			if (nome.isEmpty() || material.isEmpty() || categoria.isEmpty() || tamanho.isEmpty() || variacao.isEmpty()
					|| String.valueOf(valor).isEmpty() || String.valueOf(estoque).isEmpty()) {
				JOptionPane.showMessageDialog(telaCadastroProduto, "Parece que você não preencheu todos os campos",
						":(", JOptionPane.ERROR_MESSAGE);
				return;
			}

			produto.setNomeProduto(nome);
			produto.setMaterial(material);
			produto.setCategoria(categoria);
			produto.setVariacao(variacao);
			produto.setValor(valor);
			produto.setQuantEstoque(estoque);
			produto.setTamanho(tamanho);

			if (produtoDAO.editarProduto(produto.getIdProduto(), produto)) {
				JOptionPane.showMessageDialog(telaCadastroProduto, "Produto Editado com Sucesso!", null,
						JOptionPane.INFORMATION_MESSAGE);
				telaProdutos.setVisible(true);
			} else {
				JOptionPane.showMessageDialog(telaCadastroProduto, "Erro ao editar produto", "Erro",
						JOptionPane.ERROR_MESSAGE);
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(telaCadastroProduto, "Valor ou Estoque preechidos incorreetamente", ":(",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	public void cadastrarProduto(TelaCadastroProdutos cadastroProduto) {
		// TODO Auto-generated method stub
		// if quase mais longo que a sua mãe.
		if (cadastroProduto.getTextFieldNome().isEmpty() || cadastroProduto.getCbMaterial().isEmpty()
				|| cadastroProduto.getCbCategoria().isEmpty() || cadastroProduto.getCbCategoria().isEmpty()
				|| cadastroProduto.getCbTamnho().isEmpty() || cadastroProduto.getTextFieldVariacao().isEmpty()
				|| cadastroProduto.getTextFieldValor().isEmpty()
				|| cadastroProduto.getTextFieldQntEstoque().isEmpty()) {
			JOptionPane.showMessageDialog(cadastroProduto, "Parece que você não prencheu todos os campos", ":(",
					JOptionPane.ERROR_MESSAGE);
			return;
		}
		try {
			float valor = Float.parseFloat(telaCadastroProduto.getTextFieldValor());
			int estoque = Integer.parseInt(telaCadastroProduto.getTextFieldQntEstoque());
			String nome = cadastroProduto.getTextFieldNome();
			String material = cadastroProduto.getCbMaterial();
			String categoria = cadastroProduto.getCbCategoria();
			String tamanho = cadastroProduto.getCbTamnho();
			String variacao = cadastroProduto.getTextFieldVariacao();

			Produto produto = new Produto();

			produto.setNomeProduto(nome);
			produto.setMaterial(material);
			produto.setCategoria(categoria);
			produto.setValor(valor);
			produto.setQuantEstoque(estoque);
			produto.setTamanho(tamanho);
			produto.setVariacao(variacao);

			if (produtoDAO.cadastrarProduto(produto)) {
				JOptionPane.showMessageDialog(cadastroProduto, "Produto Cadastrado", null,
						JOptionPane.INFORMATION_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(cadastroProduto, "Erro ao cadastrar produto", "Erro",
						JOptionPane.ERROR_MESSAGE);
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(telaCadastroProduto, "Valor ou Estoque preechidos incorreetamente", ":(",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	public void DeletProduto() {
		// TODO Auto-generated method stub
		JTable table = telaProdutos.getTable();
		int selectedRowIndex = table.getSelectedRow();
		if (selectedRowIndex == -1) {
			JOptionPane.showMessageDialog(telaProdutos, "Nenhum produto selecionado", "Erro",
					JOptionPane.ERROR_MESSAGE);
		} else {
			String firstColumnValue = table.getValueAt(selectedRowIndex, 0).toString();
			produtoDAO.deletarProduto(Integer.valueOf(firstColumnValue));
			listarProdutosTable();
		}
	}

	public void fillEdit(Produto produto) {
		telaCadastroProduto.setTextFieldId().setText(Integer.toString(produto.getIdProduto()));
		telaCadastroProduto.setTextFieldNome().setText(produto.getNomeProduto());
		telaCadastroProduto.setTextFieldValor().setText(Float.toString(produto.getValor()));
		telaCadastroProduto.setTextFieldQntEstoque().setText(Integer.toString(produto.getQuantEstoque()));
		telaCadastroProduto.setTextFieldVariacao().setText(produto.getVariacao());
	}

	public void fillPP(TelaProdutos telaProdutos) {
		telaProdutos.getTable().addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {

				JTable table = telaProdutos.getTable();
				int selectedRowIndex = table.getSelectedRow();
				if (selectedRowIndex == -1) {
				} else {
					Produto produto = new Produto();
					String firstColumnValue = table.getValueAt(selectedRowIndex, 0).toString();
					produto = produtoDAO.getId(Integer.valueOf(firstColumnValue));
					telaProdutos.getTxtId().setText(String.valueOf(produto.getIdProduto()));
					telaProdutos.getTxtNome().setText(produto.getNomeProduto());
					telaProdutos.getTxtQnt().setText(String.valueOf(produto.getQuantEstoque()));
					telaProdutos.getTxtValor().setText(String.valueOf(produto.getValor()));
				}
			}
		});
	}
	
	
}*/
