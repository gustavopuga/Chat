package br.com.insite.chat.actor.factory;

import akka.actor.Actor;
import akka.actor.UntypedActorFactory;
import br.com.insite.chat.actor.InternalChatSession;

public class InternalChatSessionFactory implements UntypedActorFactory {

	private String username;

	public InternalChatSessionFactory(String username) {
		this.username = username;
	}
	
	@Override
	public Actor create() {
		return new InternalChatSession(username);
	}
	
}
