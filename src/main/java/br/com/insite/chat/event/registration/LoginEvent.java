package br.com.insite.chat.event.registration;

import br.com.insite.chat.model.user.ChatUser;

public class LoginEvent extends RegistrationChatEvent{

	private static final long serialVersionUID = 4321543103502198715L;

	public LoginEvent(ChatUser user) {
		super(user);
	}
	
	public ChatUser getUser() {
		return super.getUser();
	}
}
