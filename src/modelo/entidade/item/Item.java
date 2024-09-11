package modelo.entidade.item;

public class Item {
	
	private Produto produo;
	private int quantidade;
	private Promocao promocao;
	public Produto getProduo() {
		return produo;
	}
	public void setProduo(Produto produo) {
		this.produo = produo;
	}
	public int getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	public Promocao getPromocao() {
		return promocao;
	}
	public void setPromocao(Promocao promocao) {
		this.promocao = promocao;
	} 

}
