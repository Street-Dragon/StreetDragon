package modelo.entidade.venda;

import modelo.entidade.pessoa.funcionario.Funcionario;

import java.util.List;

import modelo.entidade.item.Item;

public class Venda {
	private Funcionario funcionario;
	private boolean cliente;
	private float precoTotal;
	private List<Item> itens;
	
	public Funcionario getFuncionario() {
		return funcionario;
	}
	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}
	public boolean isCliente() {
		return cliente;
	}
	public void setCliente(boolean cliente) {
		this.cliente = cliente;
	}
	public float getPrecoTotal() {
		return precoTotal;
	}
	public void setPrecoTotal(float precoTotal) {
		this.precoTotal = precoTotal;
	}
	public List<Item> getItens() {
		return itens;
	}
	public void setItens(List<Item> itens) {
		this.itens = itens;
	}

}
