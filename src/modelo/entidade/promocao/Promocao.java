package modelo.entidade.promocao;

import java.util.List;
import java.util.Objects;

import modelo.entidade.produto.Produto.java;

public class Promocao {
	private int idPromocao;
	private float desconto;
	private List<Produto> produto;
	
	public Promocao(int idPromocao, float desconto, List<Produto> produto) {
		setIdPromocao(idPromocao);
		setDesconto(desconto);
		setProduto(produto);
	}
	
	public int getIdPromocao() {
		return idPromocao;
	}
	public void setIdPromocao(int idPromocao) {
		this.idPromocao = idPromocao;
	}
	public float getDesconto() {
		return desconto;
	}
	public void setDesconto(float desconto) {
		this.desconto = desconto;
	}
	public List<Produto> getProduto() {
		return produto;
	}
	public void setProduto(List<Produto> produto) {
		this.produto = produto;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Promocao other = (Promocao) obj;
		return Float.floatToIntBits(desconto) == Float.floatToIntBits(other.desconto) && idPromocao == other.idPromocao;
	}
	

	
}
