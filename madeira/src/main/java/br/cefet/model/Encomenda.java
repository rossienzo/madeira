package br.cefet.model;

public final class Encomenda {
	private int id;
	private String data;
	private String dataPrevista;
	private String dataEntrega;
	private String dataVencimento;
	private String dataPagamento;
	private String dataProntidao;
	private Cliente cliente;
	
	public Encomenda() { }
	
	public Encomenda(String data, String dataPrevista, String dataEntrega, String dataVencimento, String dataPagamento, String dataProntidao, Cliente cliente) { 
		this.data = data;
		this.dataPrevista = dataPrevista;
		this.dataEntrega = dataEntrega;
		this.dataVencimento = dataVencimento;
		this.dataPagamento = dataPagamento;
		this.dataProntidao = dataProntidao;
		this.cliente = cliente;
	}
	
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getData() {
		return this.data;
	}
	
	public void setData(String data) {
		this.data = data;
	}
	
	public String getDataPrevista() {
		return this.dataPrevista;
	}
	
	public void setDataPrevista(String dataPrevista) {
		this.dataPrevista = dataPrevista;
	}
	
	public String getDataEntrega() {
		return this.dataEntrega;
	}
	
	public void setDataEntrega(String dataEntrega) {
		this.dataEntrega = dataEntrega;
	}
	
	public String getDataVencimento() {
		return this.dataVencimento;
	}
	
	public void setDataVencimento(String dataVencimento) {
		this.dataVencimento = dataVencimento;
	}
	
	public String getDataPagamento() {
		return this.dataPagamento;
	}
	
	public void setDataPagamento(String dataPagamento) {
		this.dataPagamento = dataPagamento;
	}
	
	public String getDataProntidao() {
		return this.dataProntidao;
	}
	
	public void setDataProntidao(String dataProntidao) {
		this.dataProntidao = dataProntidao;
	}

	public Cliente getCliente() {
		return this.cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	
	
}
