package br.com.insite.chat.event.message.type;

import br.com.insite.chat.event.message.SimpleMessageEvent;
import br.com.insite.chat.model.user.ChatUser;

public class PublishNoticeEvent extends SimpleMessageEvent {

	private static final long serialVersionUID = -5651407365360959584L;
	
	public PublishNoticeEvent(ChatUser from, String message) {
		super(from, false, message);
	}
	
}
