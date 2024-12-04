	package controle.entidade.produto;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.ComboBoxEditor;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import modelo.dao.produto.ProdutoDAO;
import modelo.entidade.produto.Produto;
import visao.TelaCadastroProdutos;
import visao.TelaMensagens;
import visao.TelaProdutos;

public class ProdutoControle {

	private TelaCadastroProdutos telaCadastroProduto;
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
					else if (text.equals("Editar Produto"))
						EditProduto(telaCadastroProduto);
				
			}
		});
			telaCadastroProduto.getbtnCancelar().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				telaCadastroProduto.limparCampos();
				telaCadastroProduto.dispose();
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
					telaCadastroProduto.getbtnCadastrarProduto().setText("Cadastrar Produto");
					cadastrarProduto();

				}
		});

		telaProdutos.getBtnDeletarProd().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				TelaMensagens Tm = new TelaMensagens("Deseja mesmo deletar este produto?");
				if (Tm.getResposta()) 
					DeletProduto();
			}
		});
		
		telaProdutos.getBtnPesquisar().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				atualizarTabela();
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
			TelaMensagens Tm = new TelaMensagens("Nenhum produto selecionado", 3);
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
	
	public Produto getProdutoById(int id) {
		Produto produto = produtoDAO.getId(id);
		if (produto == null) {
			throw new IllegalStateException("Produt com ID " + id + "não encontrado.");
		}
		return produto;
	}

	public void atualizarTabela() {
		if (telaProdutos.getTxtPesquisa().getText().isEmpty()) {
			List<Produto> produtos = produtoDAO.listarProdutos();
			DefaultTableModel tableModel = (DefaultTableModel) telaProdutos.getTable().getModel();
			tableModel.setRowCount(0);
			
			for (Produto produto : produtos) {
				tableModel.addRow(new Object[] {
					produto.getIdProduto(), 
					produto.getNomeProduto(), 
					produto.getValor(), 
					produto.getQuantEstoque(),
				}); 
			} 
		} else {

			List<Produto> produtos = produtoDAO.pesquisa(telaProdutos.setTxtPesquisa(), telaProdutos.getComboBox().getSelectedIndex());
			DefaultTableModel tableModel = (DefaultTableModel) telaProdutos.getTable().getModel();
			tableModel.setRowCount(0);
			
			for (Produto produto : produtos) {
				tableModel.addRow(new Object[] {
					produto.getIdProduto(), 
					produto.getNomeProduto(), 
					produto.getValor(), 
					produto.getQuantEstoque(),
				}); 
			} 
		}
	}
  
	public void EditProduto(TelaCadastroProdutos cadastroProduto) { 
		if (cadastroProduto.getTextFieldNome().isEmpty() || cadastroProduto.getCbMaterial().isEmpty() || cadastroProduto.getCbCategoria().isEmpty() || cadastroProduto.getCbCategoria().isEmpty() || cadastroProduto.getCbTamnho().isEmpty() || cadastroProduto.getTextFieldVariacao().isEmpty() || cadastroProduto.getTextFieldValor().isEmpty() || cadastroProduto.getTextFieldQntEstoque().isEmpty()) { 
			TelaMensagens Tm = new TelaMensagens("Parece que você não prencheu todos os campos", 3);
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
			int id = cadastroProduto.setTextFieldId();
			if (produtoDAO.ForncedorEx(cadastroProduto.getTextFieldFornecedor())) {
				Produto produto = new Produto();
				
				produto.setIdProduto(id);
				produto.setNomeProduto(nome); produto.setMaterial(material);
				produto.setCategoria(categoria); produto.setValor(valor);
				produto.setQuantEstoque(estoque); produto.setTamanho(tamanho);
				produto.setVariacao(variacao);
				produto.setFornecedorid(produtoDAO.FornecedorID(cadastroProduto.getTextFieldFornecedor()));
				
				
				if (produtoDAO.editarProduto(produto)) {
					TelaMensagens Tm = new TelaMensagens("Produto Editado com Sucesso!", 0);
					telaCadastroProduto.dispose();
					atualizarTabela();
				} else {
					TelaMensagens Tm = new TelaMensagens("Erro ao editar produto", 1);
				} 
			}
			
		} catch (Exception e) {
			if (!(verificarNum(telaCadastroProduto.getTextFieldValor(),2)) && !(verificarNum(telaCadastroProduto.getTextFieldQntEstoque(), 1)))
				new TelaMensagens("Valor e Estoque prechidos incorretamente", 3);
			else if (!verificarNum(telaCadastroProduto.getTextFieldValor(), 2))
				new TelaMensagens("Valor prechido incorretamente", 3);	
			else
				new TelaMensagens("Estoque prechido incorretamente", 3);
		} 
	}
  
	public void cadastrarProduto(TelaCadastroProdutos cadastroProduto) { 
		if (cadastroProduto.getTextFieldNome().isEmpty() || cadastroProduto.getCbMaterial().isEmpty() ||cadastroProduto.getCbCategoria().isEmpty() ||cadastroProduto.getCbCategoria().isEmpty() ||cadastroProduto.getCbTamnho().isEmpty() ||cadastroProduto.getTextFieldVariacao().isEmpty() ||cadastroProduto.getTextFieldValor().isEmpty() ||cadastroProduto.getTextFieldQntEstoque().isEmpty()) {
			TelaMensagens Tm = new TelaMensagens("Parece que você não prencheu todos os campos", 3);
			return; 
		} 
		try { 
			int estoque = Integer.parseInt(telaCadastroProduto.getTextFieldQntEstoque()); 
			float valor = Float.parseFloat(telaCadastroProduto.getTextFieldValor());
			String nome = cadastroProduto.getTextFieldNome(); 
			String material = cadastroProduto.getCbMaterial(); 
			String categoria = cadastroProduto.getCbCategoria();
			String tamanho = cadastroProduto.getCbTamnho(); 
			String variacao = cadastroProduto.getTextFieldVariacao();
			String NomeFornecedor = cadastroProduto.getTextFieldFornecedor();
			if (produtoDAO.ForncedorEx(NomeFornecedor)) {
				int FornecedorId = produtoDAO.FornecedorID(NomeFornecedor);
				Produto produto = new Produto();
				
				produto.setNomeProduto(nome); 
				produto.setValor(valor);
				produto.setQuantEstoque(estoque); 
				produto.setVariacao(variacao);
				produto.setMaterial(material);
				produto.setCategoria(categoria);
				produto.setTamanho(tamanho);
				produto.setFornecedorid(FornecedorId);
				if (produtoDAO.cadastrarProduto(produto)) {
					TelaMensagens Tm = new TelaMensagens("Produto Cadastrado", 0);
					atualizarTabela();
					telaCadastroProduto.dispose();
				} else {
					TelaMensagens Tm = new TelaMensagens("Erro ao cadastrar produto", 1);
				} 
			} else {
				TelaMensagens Tm = new TelaMensagens("Erro ao registrar o Fornecedor", 1);
			}
		} catch (Exception e) {
			if (!(verificarNum(telaCadastroProduto.getTextFieldValor(),2)) && !(verificarNum(telaCadastroProduto.getTextFieldQntEstoque(), 1)))
				new TelaMensagens("Valor e Estoque prechidos incorretamente", 3);
			else if (!verificarNum(telaCadastroProduto.getTextFieldValor(), 2))
				new TelaMensagens("Valor prechido incorretamente", 3);	
			else
				new TelaMensagens("Estoque prechido incorretamente", 3);			
		} 
	}
  
	public boolean verificarNum(String Num, int i) {
		if (i == 1) {
			try {
				int i2 = Integer.parseInt(Num);
				return true;
			} catch (Exception e) {
				return false;
			}
		} else {
			try {
				float i2 = Float.parseFloat(Num);
				return true;
			} catch (Exception e) {
				return false;
			}
		}
	}
	public void DeletProduto() { 
		JTable table = telaProdutos.getTable(); 
		int selectedRowIndex = table.getSelectedRow(); 
		if (selectedRowIndex == -1) { 
			TelaMensagens Tm = new TelaMensagens("Nenhum produto selecionado", 3);
		} else {
			String firstColumnValue = table.getValueAt(selectedRowIndex, 0).toString();
			produtoDAO.deletarProduto(Integer.valueOf(firstColumnValue));
			atualizarTabela(); 
		} 
	}
	  
	public void fillEdit(Produto produto) {
		telaCadastroProduto.getTextFieldId().setText(Integer.toString(produto.getIdProduto()));
		telaCadastroProduto.setTextFieldNome().setText(produto.getNomeProduto());
		telaCadastroProduto.setTextFieldValor().setText(Float.toString(produto.getValor()));
		telaCadastroProduto.setTextFieldQntEstoque().setText(Integer.toString(produto.getQuantEstoque()));
		telaCadastroProduto.setTextFieldVariacao().setText(produto.getVariacao());
		telaCadastroProduto.setTextFieldFornecedor().setText(produtoDAO.getIdF(produto.getFornecedorid()));
		CheckBoxF(produto);
	}
	
	public void fillPP(TelaProdutos telaProdutos) {
		telaProdutos.getTable().addMouseListener(new MouseAdapter() { 
				public void mouseClicked(MouseEvent e) {
			 
					JTable table = telaProdutos.getTable(); 
					int selectedRowIndex = table.getSelectedRow(); 
					if (selectedRowIndex == -1) {						
					} else { 
						Produto	produto = new Produto(); 
						String firstColumnValue = table.getValueAt(selectedRowIndex, 0).toString(); 
						produto = produtoDAO.getId(Integer.valueOf(firstColumnValue));
						telaProdutos.getTxtId().setText(String.valueOf(produto.getIdProduto()));
						telaProdutos.getTxtNome().setText(produto.getNomeProduto());
						telaProdutos.getTxtValor().setText(String.valueOf(produto.getValor())); 
						telaProdutos.getTxtQnt().setText(String.valueOf(produto.getQuantEstoque()));
						telaProdutos.getTxtCategoria().setText(produto.getCategoria());
						String fornecedor = produtoDAO.getIdF(produto.getFornecedorid());
						telaProdutos.getTxtFornecedor().setText(fornecedor);
						
					} 
			}
		}); 
	}
	public void CheckBoxF(Produto produto) {
		String Material = produto.getMaterial();
		String Categoria = produto.getCategoria();
		String Tamanho = produto.getTamanho();
		int index1 = 0;
		int index2 = 0;
		int index3 = 0;
		
		switch(Material) {
		case"Algodão":
			index1 = 1;
			break;
		case"Lã":
			index1 = 2;
		break;
		case"Seda":
			index1 = 3;
		break;
		case"Viscose":
			index1 = 4;
		break;
		case"Tencel":
			index1 = 5;
		break;
		case"Linho":
			index1 = 6;
		break;
		case"Poliéster":
			index1 = 7;
		break;
		case"Elastano":
			index1 = 8;
		break;
		case"Outro":
			index1 = 9;
		break;
		}
		switch(Categoria) {
		case"Calça":
			index2 = 1;
			break;
		case"Camisa":
			index2 = 2;
		break;
		case"Camiseta":
			index2 = 3;
		break;
		case"Moleton":
			index2 = 4;
		break;
		case"Boné":
			index2 = 5;
		break;
		case"Toca":
			index2 = 6;
		break;
		case"Tênis":
			index2 = 7;
		break;
		case"Acessórios":
			index2 = 8;
		break;
		case"Outro":
			index2 = 9;
		break;
		}
		switch(Tamanho) {
		case"PP":
			index3 = 1;
			break;
		case"P":
			index3 = 2;
		break;
		case"M":
			index3 = 3;
		break;
		case"G":
			index3 = 4;
		break;
		case"GG":
			index3 = 5;
		break;
		case"XG":
			index3 = 6;
		break;
		case"XGG":
			index3 = 7;
		break;
		case"EG":
			index3 = 8;
		break;
		case"EGG":
			index3 = 9;
		break;
		}
		telaCadastroProduto.setCbMaterial().setSelectedIndex(index1);
		telaCadastroProduto.setCbCategoria().setSelectedIndex(index2);
		telaCadastroProduto.setCbTamanho().setSelectedIndex(index3);
	}
}