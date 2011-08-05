package br.com.insite.chat.event.message;

import java.util.List;

import br.com.insite.chat.model.user.ChatUser;

public abstract class ComplexMessageEvent extends ChatMessageEvent{

	private static final long serialVersionUID = 957577115792079633L;
	
	private final List<String> messages;

	protected ComplexMessageEvent(ChatUser from, boolean privateMessage, List<String> messages) {
		super(from, privateMessage);
		this.messages = messages;
	}

	public List<String> getMessages() {
		return messages;
	}
	
}
