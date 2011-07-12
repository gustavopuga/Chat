package br.com.insite.chat.event.message;

import br.com.insite.chat.user.Interviewed;
import br.com.insite.chat.user.Moderator;

public class ModeratorMessageEvent extends ChatMessageEvent {

	private static final long serialVersionUID = -8204735939036390451L;

	public ModeratorMessageEvent(Moderator form, Interviewed to, String message) {
		super(form, to, message);	
	}

}
