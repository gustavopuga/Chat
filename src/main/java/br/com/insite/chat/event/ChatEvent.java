package br.com.insite.chat.event;

import java.io.Serializable;

import br.com.insite.chat.model.user.ChatUser;

public abstract class ChatEvent implements Serializable {

	private static final long serialVersionUID = -2864695789336406840L;
	
	private final ChatUser user;

	protected ChatEvent(ChatUser user) {
		this.user = user;
	}

	protected ChatUser getUser() {
		return user;
	}

}
