package br.com.insite.chat.chat_room.factory;

import akka.actor.Actor;
import akka.actor.UntypedActorFactory;
import br.com.insite.chat.chat_room.ChatStorageMessage;

public class ChatStorageMessageFactory implements UntypedActorFactory{

	@Override
	public Actor create() {
		return new ChatStorageMessage();
	}

}
