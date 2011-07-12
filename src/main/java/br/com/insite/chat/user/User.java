package br.com.insite.chat.user;

import br.com.insite.chat.event.message.UserMessageEvent;

public class User extends ChatUser{

	public User(String name) {
		super(name);
	}

	@Override
	public void post(String message) {
		
		postMessageEvent(new UserMessageEvent(this, new Moderator("s"), formatMessageAddUsername(message)));
	}

}
