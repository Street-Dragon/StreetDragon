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
import visao.TelaDeletarProduto;
import visao.TelaProdutos;

public class ProdutoControle {

	private TelaCadastroProdutos telaCadastroProduto;
	private TelaDeletarProduto telaDeletarProduto;
	private TelaProdutos telaProdutos;
	private ProdutoDAO produtoDAO = new ProdutoDAO();

	public void setTelaCadastrarProduto(TelaCadastroProdutos telaCadastroProduto) {
		this.telaCadastroProduto = telaCadastroProduto;
		telaCadastroProduto.getbtnCadastrarProduto().addActionListener(new ActionListener() {
			@Override
				public void actionPerformed(ActionEvent e) {
				String text = telaCadastroProduto.getTitle();
				if (text.equals("Cadastrar Produto")) 
					cadastrarProduto(telaCadastroProduto);
				else
					System.out.println("Cu");
					EditProduto(telaCadastroProduto);
				
			}
		});
			telaCadastroProduto.getbtnCancelar().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				telaCadastroProduto.limparCampos();
				telaCadastroProduto.getTextFieldId().setText(String.valueOf(produtoDAO.Idshow()));
			}
		});
		
	}
	public void setTelaProdutos(TelaProdutos telaProdutos) {
		this.telaProdutos = telaProdutos;

		telaProdutos.getBtnEditProd().addActionListener(new ActionListener() {
			@Override

			public void actionPerformed(ActionEvent e) {
					telaCadastroProduto.setTitle("Editar Produto");
					Produto produto = new Produto();
					editarProduto();

				}
		});
		telaProdutos.getBtnCadastrarProd().addActionListener(new ActionListener() {
			@Override

			public void actionPerformed(ActionEvent e) {
					telaCadastroProduto.setTitle("Cadastrar Produto");
					cadastrarProduto();

				}
		});

		telaProdutos.getBtnDeletarProd().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				DeletProduto();

			}
		});

	}

	

	protected void cadastrarProduto() {
		System.out.println("Cadastro");
		telaCadastroProduto.setVisible(true);
		telaCadastroProduto.limparCampos();
		telaCadastroProduto.getTextFieldId().setText(String.valueOf(produtoDAO.Idshow()));
		
	}

	protected void editarProduto() {
		System.out.println("edit");
		JTable table = telaProdutos.getTable(); int selectedRowIndex = table.getSelectedRow(); 
		if (selectedRowIndex == -1) {
			JOptionPane.showMessageDialog(telaProdutos, "Nenhum produto selecionado", "Erro", JOptionPane.ERROR_MESSAGE); 
		} else {
			telaCadastroProduto.setVisible(true);
			telaCadastroProduto.getbtnCadastrarProduto().setText("Editar Produto");
			telaCadastroProduto.getbtnCadastrarProduto().setText("Editar Protuto");
			telaCadastroProduto.setTitle("Editar Produto"); 
			String firstColumnValue = table.getValueAt(selectedRowIndex, 0).toString();
			Produto produto = new Produto();
			produto = produtoDAO.getId(Integer.valueOf(firstColumnValue));
			telaCadastroProduto.setVisible(true);
			telaCadastroProduto.limparCampos();
			fillEdit(produto); 
		}
	}

	public void atualizarTabela() {
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

  //-----------------------------------------------------------------------------
  public void listarProdutosTable() { List<Produto> produtos =
  produtoDAO.listarProdutos(); DefaultTableModel tableModel =
  (DefaultTableModel) telaProdutos.getTable().getModel();
  tableModel.setRowCount(0);
  
  for (Produto produto : produtos) { tableModel.addRow(new Object[] {
  produto.getIdProduto(), produto.getNomeProduto(), produto.getValor(),
  produto.getQuantEstoque(), }); } }
  
  public void EditProduto(TelaCadastroProdutos cadastroProduto) { 
	  if
	  (cadastroProduto.getTextFieldNome().isEmpty() ||
	  cadastroProduto.getCbMaterial().isEmpty() ||
	  cadastroProduto.getCbCategoria().isEmpty() ||
	  cadastroProduto.getCbCategoria().isEmpty() ||
	  cadastroProduto.getCbTamnho().isEmpty() ||
	  cadastroProduto.getTextFieldVariacao().isEmpty() ||
	  cadastroProduto.getTextFieldValor().isEmpty() ||
	  cadastroProduto.getTextFieldQntEstoque().isEmpty()) {
	  JOptionPane.showMessageDialog(cadastroProduto,
	  "Parece que você não prencheu todos os campos", ":(",
	  JOptionPane.ERROR_MESSAGE); return; } try { float valor =
	  Float.parseFloat(telaCadastroProduto.getTextFieldValor()); int estoque =
	  Integer.parseInt(telaCadastroProduto.getTextFieldQntEstoque()); String nome =
	  cadastroProduto.getTextFieldNome(); String material =
	  cadastroProduto.getCbMaterial(); String categoria =
	  cadastroProduto.getCbCategoria(); String tamanho =
	  cadastroProduto.getCbTamnho(); String variacao =
	  cadastroProduto.getTextFieldVariacao();
	  int id = cadastroProduto.setTextFieldId();
	  
	  Produto produto = new Produto();
	  
	  produto.setIdProduto(id);
	  produto.setNomeProduto(nome); produto.setMaterial(material);
	  produto.setCategoria(categoria); produto.setValor(valor);
	  produto.setQuantEstoque(estoque); produto.setTamanho(tamanho);
	  produto.setVariacao(variacao);
	  
	  
	  if (produtoDAO.editarProduto(produto)) {
		  JOptionPane.showMessageDialog(telaCadastroProduto, "Produto Editado com Sucesso!", null, JOptionPane.INFORMATION_MESSAGE);
		  telaCadastroProduto.dispose();
		  atualizarTabela();
	  } else {
		  JOptionPane.showMessageDialog(telaCadastroProduto, "Erro ao editar produto", "Erro", JOptionPane.ERROR_MESSAGE); 
	  } 
	  } catch (Exception e) {
		  JOptionPane.showMessageDialog(telaCadastroProduto,
				  "Valor ou Estoque preechidos incorreetamente", ":(",
				  JOptionPane.ERROR_MESSAGE); 
	  } 
  }
  
  public void cadastrarProduto(TelaCadastroProdutos cadastroProduto) {
	  // if quase mais longo que a sua mãe. 
  if (cadastroProduto.getTextFieldNome().isEmpty() || cadastroProduto.getCbMaterial().isEmpty() ||cadastroProduto.getCbCategoria().isEmpty() ||cadastroProduto.getCbCategoria().isEmpty() ||cadastroProduto.getCbTamnho().isEmpty() ||cadastroProduto.getTextFieldVariacao().isEmpty() ||cadastroProduto.getTextFieldValor().isEmpty() ||cadastroProduto.getTextFieldQntEstoque().isEmpty()) {
	  JOptionPane.showMessageDialog(cadastroProduto, "Parece que você não prencheu todos os campos", ":(", JOptionPane.ERROR_MESSAGE);
	  return; 
  } try { 
	  float valor =
	  Float.parseFloat(telaCadastroProduto.getTextFieldValor());
	  int estoque = Integer.parseInt(telaCadastroProduto.getTextFieldQntEstoque()); 
	  String nome = cadastroProduto.getTextFieldNome(); 
	  String material = cadastroProduto.getCbMaterial(); 
	  String categoria = cadastroProduto.getCbCategoria();
	  String tamanho = cadastroProduto.getCbTamnho(); 
	  String variacao = cadastroProduto.getTextFieldVariacao();
  
	  Produto produto = new Produto();
	  
	  produto.setNomeProduto(nome); produto.setMaterial(material);
	  produto.setCategoria(categoria); produto.setValor(valor);
	  produto.setQuantEstoque(estoque); produto.setTamanho(tamanho);
	  produto.setVariacao(variacao);
  
	  if (produtoDAO.cadastrarProduto(produto)) {
		  JOptionPane.showMessageDialog(cadastroProduto, "Produto Cadastrado", null, JOptionPane.INFORMATION_MESSAGE); 
		  atualizarTabela();
		  telaCadastroProduto.dispose();
	  } else {
		  JOptionPane.showMessageDialog(cadastroProduto, "Erro ao cadastrar produto", "Erro", JOptionPane.ERROR_MESSAGE); 
	  } 
  	  } catch (Exception e) {
  		  JOptionPane.showMessageDialog(telaCadastroProduto, "Valor ou Estoque preechidos incorreetamente", ":(", JOptionPane.ERROR_MESSAGE);
  	  } 
  }
  
  public void DeletProduto() { 
	  JTable table
  = telaProdutos.getTable(); int selectedRowIndex = table.getSelectedRow(); if
  (selectedRowIndex == -1) { JOptionPane.showMessageDialog(telaProdutos,
  "Nenhum produto selecionado", "Erro", JOptionPane.ERROR_MESSAGE); } else {
  String firstColumnValue = table.getValueAt(selectedRowIndex, 0).toString();
  produtoDAO.deletarProduto(Integer.valueOf(firstColumnValue));
  listarProdutosTable(); } }
  
  public void fillEdit(Produto produto) {
  telaCadastroProduto.getTextFieldId().setText(Integer.toString(produto.
  getIdProduto()));
  telaCadastroProduto.setTextFieldNome().setText(produto.getNomeProduto());
  telaCadastroProduto.setTextFieldValor().setText(Float.toString(produto.
  getValor()));
  telaCadastroProduto.setTextFieldQntEstoque().setText(Integer.toString(produto
  .getQuantEstoque()));
  telaCadastroProduto.setTextFieldVariacao().setText(produto.getVariacao()); }
  
  public void fillPP(TelaProdutos telaProdutos) {
  telaProdutos.getTable().addMouseListener(new MouseAdapter() { public void
  mouseClicked(MouseEvent e) {
  
  JTable table = telaProdutos.getTable(); int selectedRowIndex =
  table.getSelectedRow(); if (selectedRowIndex == -1) { } else { Produto
  produto = new Produto(); String firstColumnValue =
  table.getValueAt(selectedRowIndex, 0).toString(); produto =
  produtoDAO.getId(Integer.valueOf(firstColumnValue));
  telaProdutos.getTxtId().setText(String.valueOf(produto.getIdProduto()));
  telaProdutos.getTxtNome().setText(produto.getNomeProduto());
  telaProdutos.getTxtQnt().setText(String.valueOf(produto.getQuantEstoque()));
  telaProdutos.getTxtValor().setText(String.valueOf(produto.getValor())); } }
  }); }
  
  
  }
 
