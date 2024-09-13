package modelo.entidade.produto;


import modelo.entidade.pessoa.fornecedor.Fornecedor;
import modelo.enumeracao.tamanho.Tamanho;

public class Produto {
	private int idProduto;
	private String nomeProduto;
	private String material;
	private String categoria;
	private String variacao;
	private float valor;
	private int quantEstoque;
	private Fornecedor Fornecedor;
	private Tamanho tamanho;
	
	public int getIdProduto() {
		return idProduto;
	}
	public void setIdProduto(int idProduto) {
		this.idProduto = idProduto;
	}
	public String getNomeProduto() {
		return nomeProduto;
	}
	public void setNomeProduto(String nomeProduto) {
		this.nomeProduto = nomeProduto;
	}
	public String getMaterial() {
		return material;
	}
	public void setMaterial(String material) {
		this.material = material;
	}
	public String getCategoria() {
		return categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	public String getVariacao() {
		return variacao;
	}
	public void setVariacao(String variacao) {
		this.variacao = variacao;
	}
	public float getValor() {
		return valor;
	}
	public void setValor(float valor) {
		this.valor = valor;
	}
	public int getQuantEstoque() {
		return quantEstoque;
	}
	public void setQuantEstoque(int quantEstoque) {
		this.quantEstoque = quantEstoque;
	}
	public Fornecedor getFornecedor() {
		return Fornecedor;
	}
	public void setFornecedor(Fornecedor fornecedor) {
		Fornecedor = fornecedor;
	}
	public Tamanho getTamanho() {
		return tamanho;
	}
	public void setTamanho(Tamanho tamanho) {
		this.tamanho = tamanho;
	}

}
