package controle.entidade.produto;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelo.dao.produto.ProdutoDAO;
import modelo.entidade.produto.Produto;
import visao.TelaCadastroProdutos;
import visao.TelaDeletarProduto;
import visao.TelaListarProdutos;

public class ProdutoControle {
	private TelaCadastroProdutos telaCProduto;
	private TelaDeletarProduto telaDProduto;
	private TelaListarProdutos telaLProduto;
	private ProdutoDAO pDAO = new ProdutoDAO();
	
	public ProdutoControle(TelaListarProdutos telaLProduto) {
	    this.telaLProduto = telaLProduto;
	}

	
	
	public void setCadastroProduto(TelaCadastroProdutos cadastroProduto) {
		this.telaCProduto = cadastroProduto;
		cadastroProduto.getbtnCadastrarProduto().addActionListener(new ActionListener() {
			@Override
			
			public void actionPerformed(ActionEvent e) {
				cadastrarProduto();
				telaCProduto.ClearText();
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
	
	public void listarProdutosTable() {
	    List<Produto> produtos = pDAO.listarProdutos();
	    DefaultTableModel tableModel = (DefaultTableModel) telaLProduto.gettable().getModel();
	    
	    // Clear existing rows
	    tableModel.setRowCount(0);
	    //System.out.println(produtos.get(0).getNomeProduto());
	    
	    for (Produto produto : produtos) {
	        tableModel.addRow(new Object[]{
	            produto.getIdProduto(),
	            produto.getNomeProduto(),
	            produto.getValor(),
	            produto.getQuantEstoque(),                
	        });
	    }		
	}

	
	private void cadastrarProduto() {
		// TODO Auto-generated method stub
			String nome = telaCProduto.getTextFieldNome();
			String material = telaCProduto.getCbMaterial();
			String categoria = telaCProduto.getCbCategoria();
			float valor = telaCProduto.getTextFieldValor();
			int estoque = telaCProduto.getTextFieldQntEstoque();
			String tamanho = telaCProduto.getCbTamnho();
			String variacao = telaCProduto.getTextFieldVariacao();
		
			if (nome.isEmpty()||material.isEmpty()||categoria.isEmpty()||categoria.isEmpty()||tamanho.isEmpty()||variacao.isEmpty()|| String.valueOf(valor).isEmpty()||String.valueOf(estoque).isEmpty()) {
				JOptionPane.showMessageDialog(telaCProduto, "Parece que você não prencheu todos os campos", ":(", JOptionPane.ERROR_MESSAGE);
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
			JOptionPane.showMessageDialog(telaCProduto, "Produto Cadastrado",null, JOptionPane.INFORMATION_MESSAGE);
		} else {
			JOptionPane.showMessageDialog(telaCProduto, "Erro ao cadastrar produto","Erro", JOptionPane.ERROR_MESSAGE);
		}
	}
}
