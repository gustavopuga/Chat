package br.com.insite.chat.event.message;

import br.com.insite.chat.event.ChatEvent;
import br.com.insite.chat.model.user.ChatUser;

public abstract class ChatMessageEvent extends ChatEvent {

	private static final long serialVersionUID = -4954974206206270834L;

	private final boolean privateMessage;
	
	protected ChatMessageEvent(ChatUser from, boolean privateMessage) {
		super(from);
		this.privateMessage = privateMessage;
	}

	public ChatUser getFrom() {
		return super.getUser();
	}

	public boolean isPrivateMessage() {
		return privateMessage;
	}
	
	public boolean isPublicMessage() {
		return !privateMessage;
	}
	
	public abstract String getFormatMessage();

}
