package br.com.insite.chat.event.message.type;

import br.com.insite.chat.event.message.SimpleMessageEvent;
import br.com.insite.chat.model.user.ChatUser;

public class PrivateChatMessageEvent extends SimpleMessageEvent {

	private static final long serialVersionUID = -8074296129618739428L;
	
	public PrivateChatMessageEvent(ChatUser from, String message) {
		super(from, true, message);
	}

}
