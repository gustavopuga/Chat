package br.com.insite.chat.event;

import br.com.insite.chat.user.ChatUser;

public class LoginEvent extends ChatEvent{

	private static final long serialVersionUID = 4321543103502198715L;

	public LoginEvent(ChatUser user) {
		super(user);
	}
	
	public ChatUser getUser() {
		return super.getUser();
	}
}
