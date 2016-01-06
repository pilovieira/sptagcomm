package br.com.pilovieira.sptagcomm;

public class MessageImpl implements Message {
	
	private String message;

	public MessageImpl(String message) {
		this.message = message;
	}

	@Override
	public String getValue() {
		return message;
	}
}
