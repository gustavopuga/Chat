package br.com.insite.chat.event.message;

import br.com.insite.chat.event.ChatEvent;
import br.com.insite.chat.user.ChatUser;

public abstract class ChatMessageEvent extends ChatEvent {

	private static final long serialVersionUID = -4954974206206270834L;
	
	private final String message;
	private final ChatUser to;

	protected ChatMessageEvent(ChatUser form, ChatUser to, String message) {
		super(form);
		this.to = to;
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public ChatUser getFrom() {
		return super.getUser();
	}

	public ChatUser getTo() {
		return to;
	}
}
