package modelo.entidade.pessoa.cliente;

import modelo.entidade.pessoa.Pessoa;

public  class Cliente extends Pessoa {
	private String numeroCompras;

	public String getNumeroCompras() {
		return numeroCompras;
	}

	public void setNumeroCompras(String numeroCompras) {
		this.numeroCompras = numeroCompras;
	}

}
