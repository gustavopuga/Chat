package br.com.insite.chat.event;

import br.com.insite.chat.user.ChatUser;

public class LogoutEvent extends ChatEvent {

	private static final long serialVersionUID = -2648348397423433176L;

	public LogoutEvent(ChatUser user) {
		super(user);
	}
	
	public ChatUser getUser() {
		return super.getUser();
	}
}
