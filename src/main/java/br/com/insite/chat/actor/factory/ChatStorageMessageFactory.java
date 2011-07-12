package br.com.insite.chat.actor.factory;

import akka.actor.Actor;
import akka.actor.UntypedActorFactory;
import br.com.insite.chat.actor.ChatStorageMessage;

public class ChatStorageMessageFactory implements UntypedActorFactory{

	@Override
	public Actor create() {
		return new ChatStorageMessage();
	}

}
