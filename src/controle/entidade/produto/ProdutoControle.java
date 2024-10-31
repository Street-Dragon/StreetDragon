package controle.entidade.produto;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

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
	
	
	public void setCadastroProduto(TelaCadastroProdutos cadastroProduto) {
		this.telaCProduto = cadastroProduto;
		cadastroProduto.getbtnCadastrarProduto().addActionListener(new ActionListener() {
			@Override
			
			public void actionPerformed(ActionEvent e) {
				cadastrarProduto();
				telaCProduto.ClearText();
			}

			private void cadastrarProduto() {
				// TODO Auto-generated method stub
					String nome = telaCProduto.getTextFieldNome();
					String material = telaCProduto.getCbMaterial();
					String categoria = telaCProduto.getCbCategoria();
					int valor = telaCProduto.getTextFieldValor();
					int estoque = telaCProduto.getTextFieldQntEstoque();
					String tamanho = telaCProduto.getCbTamnho();
					String variacao = telaCProduto.getTextFieldVariacao();
				
					if (nome.isEmpty()||material.isEmpty()||categoria.isEmpty()||categoria.isEmpty()||tamanho.isEmpty()||variacao.isEmpty()|| String.valueOf(valor).isEmpty()||String.valueOf(estoque).isEmpty()) {
						JOptionPane.showMessageDialog(telaCProduto, "Parece que você não prencheu todos os campos", ":(", JOptionPane.ERROR_MESSAGE);
			            return;
					}
					
				
				Produto produto = new Produto();
			}
		});
	}
}
