package modelo.entidade.pessoa;

import modelo.entidade.contato.Contato;

public abstract class Pessoa {
	
	private int id;
	private String nome;
	private Contato contato;
	private String cpf;
	
    public Pessoa() {
        this.contato = new Contato();
    }
    
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Contato getContato() {
		return contato;
	}
	public void setContato(Contato contato) {
		this.contato = contato;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
	
}
