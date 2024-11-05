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
import visao.TelaListarProdutos;
import visao.TelaLogin;
import visao.TelaPrincipal;
import visao.TelaProdutos;

public class ProdutoControle {
	private TelaCadastroProdutos telaCProduto;	
    private TelaEditarProduto telaEProduto;
    private TelaListarProdutos telaLProduto;
    private TelaProdutos telaP;
    private TelaLogin telaLogin;
    private TelaPrincipal telaPrincipal;
    private ProdutoDAO pDAO = new ProdutoDAO();

    public void setTelaListarProdutos(TelaListarProdutos telaLProduto) {
        this.telaLProduto = telaLProduto;
    }
    public void setTelaEditarProduto(TelaEditarProduto telaEProduto) {
        this.telaEProduto = telaEProduto;
    }
    public void setTelaCadastrarProduto(TelaCadastroProdutos telaCProduto) {
    	this.telaCProduto = telaCProduto;
    }
    //---------------------------------------------------------------------------------------
    public void setCadastroProduto(TelaProdutos telaP) {
	    this.telaP = telaP;
	    telaP.getBtnCadastrarProd().addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	            telaCProduto.setVisible(true);
	        }
	    });
	}
    
    public void setDeletarProduto(TelaProdutos telaP) {
	    this.telaP = telaP;
	    listarProdutosTable2();
	    telaP.getBtnDeletarProd().addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	        	DeletProduto();
	            listarProdutosTable2();
	        }
	    });
	}
    
    
    /*public void setCadastroFuncionario(TelaCadastroFuncionario cadastroFuncionario) {
		this.cadastroFuncionario = cadastroFuncionario;
		atualizarTabela();
		cadastroFuncionario.getBtnCadastrarFuncionario().addActionListener(new ActionListener() {
			@Override

			public void actionPerformed(ActionEvent e) {
				cadastrarFuncionario();
				atualizarTabela();
				cadastroFuncionario.limparCampos();
			}
		});
	}*/
    //-----------------------------------------------------------------------------------------

	public void setTelaCadastro(TelaProdutos listarProduto) {
		this.telaP = listarProduto;
		listarProduto.getBtnCadastrarProd().addActionListener(new ActionListener() {
			@Override
			
			public void actionPerformed(ActionEvent e) {
				telaCProduto.setVisible(true);
			}
		});
	}
    
    public void listarProdutosTable() {
        if (telaLProduto == null) {
            throw new IllegalStateException("TelaListarProdutos is not set");
        }

        List<Produto> produtos = pDAO.listarProdutos();
        DefaultTableModel tableModel = (DefaultTableModel) telaLProduto.gettable().getModel();
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

    public void listarProdutosTable2() {
        if (telaLProduto == null) {
            throw new IllegalStateException("TelaListarProdutos is not set");
        }

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
    

    public void editProduto(Produto produto) {
        String nome = telaEProduto.getTextFieldNome();
        String material = telaEProduto.getCbMaterial();
        String categoria = telaEProduto.getCbCategoria();
        String variacao = telaEProduto.getTextFieldVariacao();
        float valor = telaEProduto.getTextFieldValor();
        int estoque = telaEProduto.getTextFieldQntEstoque();
        String tamanho = telaEProduto.getCbTamnho();

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
            JOptionPane.showMessageDialog(telaEProduto, "Produto Editado com Sucesso!", null, JOptionPane.INFORMATION_MESSAGE);
            telaLProduto.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(telaEProduto, "Erro ao editar produto", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
	
	

	
	public void cadastrarProduto(TelaCadastroProdutos cadastroProduto) {
		// TODO Auto-generated method stub
			String nome = cadastroProduto.getTextFieldNome();
			String material = cadastroProduto.getCbMaterial();
			String categoria = cadastroProduto.getCbCategoria();
			float valor = cadastroProduto.getTextFieldValor();
			int estoque = cadastroProduto.getTextFieldQntEstoque();
			String tamanho = cadastroProduto.getCbTamnho();
			String variacao = cadastroProduto.getTextFieldVariacao();
		
			if (nome.isEmpty()||material.isEmpty()||categoria.isEmpty()||categoria.isEmpty()||tamanho.isEmpty()||variacao.isEmpty()|| String.valueOf(valor).isEmpty()||String.valueOf(estoque).isEmpty()) {
				JOptionPane.showMessageDialog(cadastroProduto, "Parece que você não prencheu todos os campos", ":(", JOptionPane.ERROR_MESSAGE);
	            return;
			}
			
		
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
	
	public void EditProduto(Produto produto) {
		// TODO Auto-generated method stub
		String nome = telaEProduto.getTextFieldNome();
		String material = telaEProduto.getCbMaterial();
		String categoria = telaEProduto.getCbCategoria();
		float valor = telaEProduto.getTextFieldValor();
		int estoque = telaEProduto.getTextFieldQntEstoque();
		String tamanho = telaEProduto.getCbTamnho();
		String variacao = telaEProduto.getTextFieldVariacao();
		
		if (nome.isEmpty()||material.isEmpty()||categoria.isEmpty()||categoria.isEmpty()||tamanho.isEmpty()||variacao.isEmpty()|| String.valueOf(valor).isEmpty()||String.valueOf(estoque).isEmpty()) {
			JOptionPane.showMessageDialog(telaEProduto, "Parece que você não prencheu todos os campos", ":(", JOptionPane.ERROR_MESSAGE);
            return;
		}
		
		Produto prod = new Produto();
		
		prod.setNomeProduto(nome);
		prod.setMaterial(material);
		prod.setCategoria(categoria);
		prod.setValor(valor);
		prod.setQuantEstoque(estoque);
		prod.setTamanho(tamanho);
		prod.setVariacao(variacao);
		
		int id = produto.getIdProduto();
		pDAO.editarProduto(id, produto);
	}
	public Produto getEdit() {
		JTable table = telaLProduto.gettable();
		int selectedRowIndex = table.getSelectedRow();
		if (selectedRowIndex == -1) {
			JOptionPane.showMessageDialog(telaLProduto, "Nenhum produto selecionado","Erro", JOptionPane.ERROR_MESSAGE);
			return null;
		} else {
			Produto produto = new Produto();
			produto = pDAO.getId(selectedRowIndex);
			telaEProduto.setVisible(true);
			return produto;
			
		}
	}
	

}
