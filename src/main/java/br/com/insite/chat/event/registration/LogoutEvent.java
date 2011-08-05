package br.com.insite.chat.event.registration;

import br.com.insite.chat.model.user.ChatUser;

public class LogoutEvent extends RegistrationChatEvent{

	private static final long serialVersionUID = -2648348397423433176L;

	public LogoutEvent(ChatUser user) {
		super(user);
	}
	
	public ChatUser getUser() {
		return super.getUser();
	}
}
