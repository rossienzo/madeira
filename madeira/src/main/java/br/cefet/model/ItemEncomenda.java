package br.cefet.model;

public final class ItemEncomenda {
	private int id;
	private int quantidade;
	private Movel movel;
	private Encomenda encomenda;
	
	public ItemEncomenda() { }
	
	public int getId() {
		return this.id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getQuantidade() {
		return this.quantidade;
	}
	
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	
	public Movel getMovel() {
		return this.movel;
	}
	
	public void setMovel(Movel movel) {
		this.movel = movel;
	}
	
	public Encomenda getEncomenda() {
		return this.encomenda;
	}
	
	public void setEncomenda(Encomenda encomenda) {
		this.encomenda = encomenda;
	}
	
	
}
