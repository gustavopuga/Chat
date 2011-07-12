package br.com.insite.chat.event.message;

import br.com.insite.chat.user.Interviewed;
import br.com.insite.chat.user.Moderator;
import br.com.insite.chat.user.User;

public class InterviewedMessageEvent extends ChatMessageEvent {

	private static final long serialVersionUID = -3807702614628943924L;

	public InterviewedMessageEvent(Interviewed form, Moderator to, String message) {
		super(form, to, message);	
	}

	public InterviewedMessageEvent(Interviewed form, User to, String message) {
		super(form, to, message);	
	}
}
