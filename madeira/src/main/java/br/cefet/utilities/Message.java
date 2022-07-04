package br.cefet.utilities;

public class Message {
	private String text;
	private String type;
	
	public Message() { }
	
	public void defineMessage(String type, String text) {
		this.type = type;
		this.text = text;
	}
	
	public String getText() {
		return this.text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getType() {
		return this.type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	
}
