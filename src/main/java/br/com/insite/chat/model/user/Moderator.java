package br.com.insite.chat.model.user;

import java.util.List;


public class Moderator extends ChatUser {

	List<Invited> intervieweds;
	
	protected Moderator(String name) {
		super(name);
	}

	public void postPrivateMessage(String message) {

	}

	public void postNotice(String message) {

	}

	@Override
	public void onReceive(Object event) throws Exception {
		
	}

}
