package modelo.entidade.pessoa.fornecedor;

import modelo.entidade.pessoa.Pessoa;

public class Fornecedor extends Pessoa{
	private int cpnj;
	private int cep;
	private String rua;
	private int numero;
	
	public int getCpnj() {
		return cpnj;
	}
	public void setCpnj(int cpnj) {
		this.cpnj = cpnj;
	}
	public int getCep() {
		return cep;
	}
	public void setCep(int cep) {
		this.cep = cep;
	}
	public String getRua() {
		return rua;
	}
	public void setRua(String rua) {
		this.rua = rua;
	}
	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}

}