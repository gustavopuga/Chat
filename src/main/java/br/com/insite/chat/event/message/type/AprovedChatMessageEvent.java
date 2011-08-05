package br.com.insite.chat.event.message.type;

import java.util.List;

import br.com.insite.chat.event.message.ComplexMessageEvent;
import br.com.insite.chat.model.user.Moderator;

public class AprovedChatMessageEvent extends ComplexMessageEvent {
	
	private static final long serialVersionUID = 4210200625973649957L;
	
	public AprovedChatMessageEvent(Moderator from, List<String> messages) {
		super(from, true, messages);
	}

	@Override
	public String getFormatMessage() {
		// TODO Auto-generated method stub
		return null;
	}

}
