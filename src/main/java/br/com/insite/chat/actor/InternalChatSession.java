package br.com.insite.chat.actor;

import java.util.Date;

import org.apache.log4j.Logger;

import akka.actor.ActorRef;
import akka.actor.Actors;
import akka.actor.UntypedActor;
import br.com.insite.chat.actor.factory.ChatStorageMessageFactory;
import br.com.insite.chat.event.message.ChatMessageEvent;

public class InternalChatSession extends UntypedActor{
	
	private static final Logger log = Logger.getLogger(InternalChatSession.class);
	
	private ActorRef storage;
    private final long loginTime = System.currentTimeMillis();

    public InternalChatSession(String user) {
            this.storage = Actors.actorOf(new ChatStorageMessageFactory()).start();
            log.info("Nova sessao criada para usuario [" + user + "] as [" + new Date(loginTime) + "]");
    }

    public void onReceive(Object event) throws Exception {
            if (event instanceof ChatMessageEvent) {
                    storage.sendOneWay(event);
            } 
    }
    
    @Override
    public void postStop() {
    	super.postStop();
    	getContext().unlink(storage);
		storage.stop();
    }

}
