package br.com.insite.chat.user;

import java.util.List;

import br.com.insite.chat.event.message.ModeratorMessageEvent;


public class Moderator extends ChatUser {

	List<Interviewed> intervieweds;
	
	protected Moderator(String name) {
		super(name);
	}

	@Override
	public void post(String message) {
		
		postMessageEvent(new ModeratorMessageEvent(this, intervieweds.get(0), message));
	}

}
