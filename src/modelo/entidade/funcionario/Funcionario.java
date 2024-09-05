package modelo.entidade.funcionario;

public class Funcionario {
	private String senhaFuncionario;
	private String cpf;
	private Boolean adm;
	
	
	public String getSenhaFuncionario() {
		return senhaFuncionario;
	}
	public void setSenhaFuncionario(String senhaFuncionario) {
		this.senhaFuncionario = senhaFuncionario;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public Boolean getAdm() {
		return adm;
	}
	public void setAdm(Boolean adm) {
		this.adm = adm;
	}
	
}
