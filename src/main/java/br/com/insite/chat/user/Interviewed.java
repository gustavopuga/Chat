package br.com.insite.chat.user;

import java.util.List;

import br.com.insite.chat.event.message.InterviewedMessageEvent;

public class Interviewed extends ChatUser{

	List<User> users;
	List<Moderator> moderators;
	
	protected Interviewed(String name) {
		super(name);
	}

	@Override
	public void post(String message) {

		for (Moderator moderator : moderators) {
			postMessageEvent(new InterviewedMessageEvent(this, moderator, formatMessageAddUsername(message)));
		}
		for (User user : users) {
			postMessageEvent(new InterviewedMessageEvent(this, user, formatMessageAddUsername(message)));
		}
	}

}
