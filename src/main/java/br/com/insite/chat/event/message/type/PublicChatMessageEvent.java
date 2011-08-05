package br.com.insite.chat.event.message.type;

import java.util.List;

import br.com.insite.chat.event.message.ComplexMessageEvent;
import br.com.insite.chat.model.user.ChatUser;

public class PublicChatMessageEvent extends ComplexMessageEvent {
	
	private static final long serialVersionUID = 9024503743359626650L;
	
	public PublicChatMessageEvent(ChatUser from, List<String> messages) {
		super(from, false, messages);
	}

	@Override
	public String getFormatMessage() {
		// TODO Auto-generated method stub
		return null;
	}

}
