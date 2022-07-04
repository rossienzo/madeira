package br.cefet.model;

public final class Movel {
	private int id;
	private String nome;
	private String modelo;
	private String descricao;
	private double preco;
	private LinhaMovel linhaMovel;
	
	public Movel() { }
	
	public Movel(int id, String nome, String modelo, String descricao, double preco) {
		this.id = id;
		this.nome = nome;
		this.modelo = modelo;
		this.descricao = descricao;
		this.preco = preco;
	}
	
	public Movel(String nome, String modelo, String descricao, double preco) {
		this.nome = nome;
		this.modelo = modelo;
		this.descricao = descricao;
		this.preco = preco;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getModelo() {
		return this.modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getDescricao() {
		return this.descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public double getPreco() {
		return this.preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}

	public LinhaMovel getLinhaMovel() {
		return this.linhaMovel;
	}

	public void setLinhaMovel(LinhaMovel linhaMovel) {
		this.linhaMovel = linhaMovel;
	}
}
