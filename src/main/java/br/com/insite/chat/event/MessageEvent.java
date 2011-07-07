package br.com.insite.chat.event;

public class MessageEvent extends ChatEvent {

	private final String message;

	public MessageEvent(String form, String message) {
		super(form);
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public String getFrom() {
		return getUser();
	}
}
