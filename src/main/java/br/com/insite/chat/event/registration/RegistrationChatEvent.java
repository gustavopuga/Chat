package br.com.insite.chat.event.registration;

import br.com.insite.chat.event.ChatEvent;
import br.com.insite.chat.model.user.ChatUser;

public abstract class RegistrationChatEvent extends ChatEvent{

	private static final long serialVersionUID = -7207962984421271226L;
	
	protected RegistrationChatEvent(ChatUser user) {
		super(user);
	}

}
