package wifi.web.controller;

public class Message {

	private MessageType type;
	private String body;
	
	public Message(MessageType type, String body) {
		this.type = type;
		this.body = body;
	}

	public MessageType getType() {
		return type;
	}

	public void setType(MessageType type) {
		this.type = type;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}
	
}
