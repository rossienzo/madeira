package br.cefet.model;

public class Cliente {

	private int id;
	private String codigo;
	private String telefone;
	private String nome;
	private String senha;
	
	public Cliente() { } 
	
	public Cliente(int id, String telefone, String nome, String senha) {
		this.id = id;
		this.telefone = telefone;
		this.nome = nome;
		this.senha = senha;
	}
	
	public Cliente(String codigo, String telefone, String nome, String senha) {
		this.codigo = codigo;
		this.telefone = telefone;
		this.nome = nome;
		this.senha = senha;
	}
	
	public Cliente(int id, String codigo, String telefone, String nome, String senha) {
		this.id = id;
		this.codigo = codigo;
		this.telefone = telefone;
		this.nome = nome;
		this.senha = senha;
	}

	public void resetarDados() {
		setNome("");
		setTelefone("");
		setSenha("");
	}
	
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getCodigo() {
		return this.codigo;
	}
	
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	
	public String getTelefone() {
		return this.telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	
	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSenha() {
		return this.senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
}
