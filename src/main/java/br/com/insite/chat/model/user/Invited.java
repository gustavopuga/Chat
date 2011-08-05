package br.com.insite.chat.model.user;

import java.util.List;

public class Invited extends ChatUser{

	List<User> users;
	List<Moderator> moderators;
	
	protected Invited(String name) {
		super(name);
	}

	public void postPrivateMessage(String message) {

	}

	public void postPublicMessage(String message) {

	}
	
	@Override
	public void onReceive(Object event) throws Exception {
		
	}

}
