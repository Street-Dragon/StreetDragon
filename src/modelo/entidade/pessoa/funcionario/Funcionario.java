package modelo.entidade.pessoa.funcionario;

import modelo.entidade.pessoa.Pessoa;

public class Funcionario  extends Pessoa{
	
	private String senhaFuncionario;
	private String cpf;
	private boolean adm;
	
	public String getSenhaFuncionario() {
		return senhaFuncionario;
	}
	public void setSenhaFuncionario(String senha) {
		this.senhaFuncionario = senha;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public boolean isAdm() {
		return adm;
	}
	public void setAdm(boolean adm) {
		this.adm = adm;
	}
	
}
