package br.com.insite.chat.event;

import java.io.Serializable;

public class ChatEvent implements Serializable {

	private final String user;

	protected ChatEvent(String user) {
		this.user = user;
	}

	protected String getUser() {
		return user;
	}

}
