package controle.entidade.produto;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import modelo.dao.produto.ProdutoDAO;
import modelo.entidade.produto.Produto;
import visao.TelaCadastroProdutos;
import visao.TelaDeletarProduto;
import visao.TelaEditarProduto;
import visao.TelaListarProdutos;
import visao.TelaMensagens;

public class ProdutoControle {
	private TelaCadastroProdutos telaCProduto;
	private TelaDeletarProduto telaDProduto;	
    private TelaEditarProduto telaEProduto;
    private TelaListarProdutos telaLProduto;
    private ProdutoDAO pDAO = new ProdutoDAO();

    public ProdutoControle(TelaListarProdutos telaLProduto) {
        this.telaLProduto = telaLProduto;
    }

    public void setTelaListarProdutos(TelaListarProdutos telaLProduto) {
        this.telaLProduto = telaLProduto;
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
    
    public ProdutoControle() {
    }
    public void setTelaEditarProduto(TelaEditarProduto telaEProduto) {
        this.telaEProduto = telaEProduto;
    }

    public Produto getProdutoById(int id) {
        Produto produto = pDAO.getId(id);
        if (produto == null) {
            throw new IllegalStateException("Produto with ID " + id + " not found.");
        }
        return produto;
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
        	TelaMensagens TM = new TelaMensagens("Parece que você não preencheu todos os campos", 3);
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
        	TelaMensagens TM = new TelaMensagens("Produto Editado com Sucesso!", 0);
            telaLProduto.setVisible(true);
        } else {
        	TelaMensagens TM = new TelaMensagens("Erro ao editar produto", 1);
        }
    }
	
	public void setCadastroProduto(TelaCadastroProdutos cadastroProduto) {
	    this.telaCProduto = cadastroProduto;
	    cadastroProduto.getbtnCadastrarProduto().addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	            //cadastrarProduto();
	            telaCProduto.ClearText();
	            telaCProduto.dispose();
	        }
	    });
	}

	public void setTelaCadastro(TelaListarProdutos listarProduto) {
		this.telaLProduto = listarProduto;
		listarProduto.getbtnNovoProduto().addActionListener(new ActionListener() {
			@Override
			
			public void actionPerformed(ActionEvent e) {
				telaCProduto.setVisible(true);
			}
		});
		
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
				TelaMensagens TM = new TelaMensagens("Parece que você não prencheu todos os campos", 3);
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
			TelaMensagens TM = new TelaMensagens("Produto Cadastrado", 0);
		} else {
			TelaMensagens TM = new TelaMensagens("Erro ao cadastrar produto", 1);
		}
	}


	public void DeletProduto(TelaListarProdutos telaL) {
		// TODO Auto-generated method stub
		JTable table = telaL.gettable();
		int selectedRowIndex = table.getSelectedRow();
		if (selectedRowIndex == -1) {
			TelaMensagens TM = new TelaMensagens("Nenhum produto selecionado", 3);
		} else {
			String firstColumnValue = table.getValueAt(selectedRowIndex, 0).toString();
			pDAO.deletarProduto(Integer.valueOf(firstColumnValue));
			listarProdutosTable();
		}
	}
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
			TelaMensagens TM = new TelaMensagens("Parece que você não prencheu todos os campos", 3);
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
			TelaMensagens TM = new TelaMensagens("Nenhum produto selecionado", 3);
			return null;
		} else {
			Produto produto = new Produto();
			produto = pDAO.getId(selectedRowIndex);
			telaEProduto.setVisible(true);
			return produto;
			
		}
	}

}
