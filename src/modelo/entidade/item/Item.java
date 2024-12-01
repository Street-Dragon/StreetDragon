package modelo.entidade.item;

import modelo.entidade.produto.Produto;
import modelo.entidade.promocao.Promocao;

public class Item {

	private Produto produto;
	private int quantidade;

	public Produto getProduto() {
		return produto;
	}
	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	public int getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

}
