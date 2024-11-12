package controle.entidade.produto;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import modelo.dao.funcionario.FuncionarioDAO;
import modelo.dao.produto.ProdutoDAO;
import modelo.entidade.produto.Produto;

import visao.TelaCadastroFuncionario;
import visao.TelaCadastroProdutos;
import visao.TelaDeletarProduto;
import visao.TelaEditarProduto;
import visao.TelaLogin;
import visao.TelaPrincipal;
import visao.TelaProdutos;

public class ProdutoControle {
	private TelaCadastroProdutos telaCProduto;	
    private TelaEditarProduto telaEProduto;
    private TelaProdutos telaP;
    private TelaLogin telaLogin;
    private TelaPrincipal telaPrincipal;
    private ProdutoDAO pDAO = new ProdutoDAO();
    

    public void setTelaCadastrarProduto(TelaCadastroProdutos telaCProduto) {
    	this.telaCProduto = telaCProduto;
    }
    
    //---------------------------------------Cadastrar-------------------------------------
     public void setCadastroProdutobtn(TelaProdutos telaP) {
        this.telaP = telaP;
        telaP.getBtnCadastrarProd().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	 if (telaCProduto == null) {
                     System.out.println("botão ta cagado");
                 } else {
                     telaCProduto.setVisible(true);
                     telaCProduto.getbtnCadastrarProduto().setText("Cadastrar Produto");
                     telaCProduto.setTitle("Cadastrar Produto");
                     telaCProduto.getTextFieldId().setText(String.valueOf(pDAO.Idshow()));
                 }
            }
        });
    }
    public void setCadastroProduto(TelaCadastroProdutos telaCProduto) {
    	this.telaCProduto = telaCProduto;
    	telaCProduto.getbtnCadastrarProduto().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	if(telaCProduto.getbtnCadastrarProduto().getText().equals("Cadastrar Produto")) {
            		cadastrarProduto(telaCProduto);
                	telaCProduto.ClearText();
                	telaCProduto.getTextFieldId().setText(String.valueOf(pDAO.Idshow()));
                	listarProdutosTable();
            	} else {
            		
            		Produto produto = new Produto();
         	 		produto = pDAO.getId(Integer.valueOf(telaCProduto.getTextFieldId().getText()));
         	 		EditProduto(produto);
         	 		listarProdutosTable();
         	 		telaCProduto.dispose();
            	}
            }
        });
    }
  //---------------------------------------Editar-------------------------------------
    public void setTelaEditarProduto(TelaProdutos telaProdutos) {
    	this.telaP = telaProdutos;
    	telaProdutos.getBtnEditProd().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                     JTable table = telaP.getTable();
             		 int selectedRowIndex = table.getSelectedRow();
             		 if (selectedRowIndex == -1) {
             		 	JOptionPane.showMessageDialog(telaP, "Nenhum produto selecionado","Erro", JOptionPane.ERROR_MESSAGE);
             		 } else {
             			telaCProduto.setVisible(true);
                        telaCProduto.getbtnCadastrarProduto().setText("Editar Produto");
                        telaCProduto.setTitle("Editar Produto");
             	 		String firstColumnValue = table.getValueAt(selectedRowIndex, 0).toString();
             	 		Produto produto = new Produto();
             	 		produto = pDAO.getId(Integer.valueOf(firstColumnValue));
             	 		telaCProduto.setVisible(true);
             	 		fillEdit(produto);	
              		} 
            }
        });
    }
  //---------------------------------------Deletar-------------------------------------
    
    public void setDeletarProduto(TelaProdutos telaP) {
	    telaP.getBtnDeletarProd().addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	        	DeletProduto();
	            listarProdutosTable();
	        }
	    });
	}
  //------------------------------------------------------------------------------------
    public void setCancelarProduto(TelaCadastroProdutos telaCProduto) {
    	this.telaCProduto = telaCProduto;
    	telaCProduto.getbtnCancelar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	telaCProduto.ClearText();
            	telaCProduto.getTextFieldId().setText(String.valueOf(pDAO.Idshow()));
            }
        });
    }
    
    public void listarProdutosTable() {
        List<Produto> produtos = pDAO.listarProdutos();
        DefaultTableModel tableModel = (DefaultTableModel) telaP.getTable().getModel();
        tableModel.setRowCount(0);

        for (Produto produto : produtos) {
            tableModel.addRow(new Object[]{
                produto.getIdProduto(),
                produto.getNomeProduto(),
                produto.getValor(),
                produto.getQuantEstoque(),
            });
        }
        }



    public Produto getProdutoById(int id) {
        Produto produto = pDAO.getId(id);
        if (produto == null) {
            throw new IllegalStateException("Produt com ID " + id + "não encontrado.");
        }
        return produto;
    }
    

    public void EditProduto(Produto produto) {
        String nome = telaCProduto.getTextFieldNome();
        String material = telaCProduto.getCbMaterial();
        String categoria = telaCProduto.getCbCategoria();
        String variacao = telaCProduto.getTextFieldVariacao();
        float valor = telaCProduto.getTextFieldValor();
        int estoque = telaCProduto.getTextFieldQntEstoque();
        String tamanho = telaCProduto.getCbTamnho();

        if (nome.isEmpty() || material.isEmpty() || categoria.isEmpty() || tamanho.isEmpty() || variacao.isEmpty() || String.valueOf(valor).isEmpty() || String.valueOf(estoque).isEmpty()) {
            JOptionPane.showMessageDialog(telaEProduto, "Parece que você não preencheu todos os campos", ":(", JOptionPane.ERROR_MESSAGE);
            return;
        }

        produto.setNomeProduto(nome);
        produto.setMaterial(material);
        produto.setCategoria(categoria);
        produto.setVariacao(variacao);
        produto.setValor(valor);
        produto.setQuantEstoque(estoque);
        produto.setTamanho(tamanho);

        if (pDAO.editarProduto(produto.getIdProduto(), produto)) {
            JOptionPane.showMessageDialog(telaCProduto, "Produto Editado com Sucesso!", null, JOptionPane.INFORMATION_MESSAGE);
            telaP.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(telaCProduto, "Erro ao editar produto", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }	

	
	public void cadastrarProduto(TelaCadastroProdutos cadastroProduto) {
		// TODO Auto-generated method stub
			//if quase mais longo que a sua mãe.
			if (cadastroProduto.getTextFieldNome().isEmpty()||cadastroProduto.getCbMaterial().isEmpty()||cadastroProduto.getCbCategoria().isEmpty()||cadastroProduto.getCbCategoria().isEmpty()||cadastroProduto.getCbTamnho().isEmpty()||cadastroProduto.getTextFieldVariacao().isEmpty()|| String.valueOf(cadastroProduto.getTextFieldValor()).isEmpty()||String.valueOf(cadastroProduto.getTextFieldQntEstoque()).isEmpty()) {
				JOptionPane.showMessageDialog(cadastroProduto, "Parece que você não prencheu todos os campos", ":(", JOptionPane.ERROR_MESSAGE);
	            return;
			}
			String nome = cadastroProduto.getTextFieldNome();
			String material = cadastroProduto.getCbMaterial();
			String categoria = cadastroProduto.getCbCategoria();
			float valor = cadastroProduto.getTextFieldValor();
			int estoque = cadastroProduto.getTextFieldQntEstoque();
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
		
		
		if (pDAO.cadastrarProduto(produto)) {
			JOptionPane.showMessageDialog(cadastroProduto, "Produto Cadastrado",null, JOptionPane.INFORMATION_MESSAGE);
		} else {
			JOptionPane.showMessageDialog(cadastroProduto, "Erro ao cadastrar produto","Erro", JOptionPane.ERROR_MESSAGE);
		}
	}
	//-----------------------------------------------------------------
	public void DeletProduto() {
		// TODO Auto-generated method stub
		JTable table = telaP.getTable();
		int selectedRowIndex = table.getSelectedRow();
		if (selectedRowIndex == -1) {
			JOptionPane.showMessageDialog(telaP, "Nenhum produto selecionado","Erro", JOptionPane.ERROR_MESSAGE);
		} else {
			String firstColumnValue = table.getValueAt(selectedRowIndex, 0).toString();
			pDAO.deletarProduto(Integer.valueOf(firstColumnValue));
			listarProdutosTable();
		}
	}
	//------------------------------------------------------------------

	public void fillEdit(Produto produto) {
		telaCProduto.setTextFieldId().setText(Integer.toString(produto.getIdProduto()));
		telaCProduto.setTextFieldNome().setText(produto.getNomeProduto());
		telaCProduto.setTextFieldValor().setText(Float.toString(produto.getValor()));
		telaCProduto.setTextFieldQntEstoque().setText(Integer.toString(produto.getQuantEstoque()));
		telaCProduto.setTextFieldVariacao().setText(produto.getVariacao());
	}
	public Produto getEdit() {
		JTable table = telaP.getTable();
		int selectedRowIndex = table.getSelectedRow();
		if (selectedRowIndex == -1) {
			JOptionPane.showMessageDialog(telaP, "Nenhum produto selecionado","Erro", JOptionPane.ERROR_MESSAGE);
			return null;
		} else {
			Produto produto = new Produto();
			produto = pDAO.getId(selectedRowIndex);
			telaEProduto.setVisible(true);
			return produto;
			
		}
	}
	

}
