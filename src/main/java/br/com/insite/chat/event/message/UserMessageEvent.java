package br.com.insite.chat.event.message;

import br.com.insite.chat.user.Moderator;
import br.com.insite.chat.user.User;

public class UserMessageEvent extends ChatMessageEvent {

	private static final long serialVersionUID = -5176425492779289251L;

	public UserMessageEvent(User form, Moderator to, String message) {
		super(form, to, message);	
	}

}
