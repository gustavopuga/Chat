package br.com.insite.chat.event.message;

import br.com.insite.chat.model.user.ChatUser;

public abstract class SimpleMessageEvent extends ChatMessageEvent{

	private static final long serialVersionUID = 4738894628403192674L;
	
	private final String message;

	protected SimpleMessageEvent(ChatUser from, boolean privateMessage, String message) {
		super(from, privateMessage);
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
	
	@Override
	public String getFormatMessage() {
		
		return getFrom() + ": " + message;
	}
	
}
