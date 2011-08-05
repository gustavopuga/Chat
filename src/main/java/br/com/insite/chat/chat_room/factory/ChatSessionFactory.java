package br.com.insite.chat.chat_room.factory;

import akka.actor.Actor;
import akka.actor.UntypedActorFactory;
import br.com.insite.chat.chat_room.ChatSession;

public class ChatSessionFactory implements UntypedActorFactory {

	private String username;

	public ChatSessionFactory() {
		
	}
	
	public ChatSessionFactory(String username) {
		this.username = username;
	}
	
	@Override
	public Actor create() {
		return username != null ? new ChatSession(username) : new ChatSession();
	}
	
}
