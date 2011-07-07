package br.com.insite.chat.actor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import akka.actor.ActorRef;
import akka.actor.UntypedActor;
import br.com.insite.chat.event.MessageEvent;

public class InternalChatSession extends UntypedActor{
	
	private static final Logger log = Logger.getLogger(InternalChatSession.class);
	
	private ActorRef storage;
    private final long loginTime = System.currentTimeMillis();
    private List<String> userLog = new ArrayList<String>();

    public InternalChatSession(String user, ActorRef storage) {
            this.storage = storage;
            log.info("Nova sessao criada para usuario [" + user + "] as [" + new Date(loginTime) + "]");
    }

    public void onReceive(Object event) throws Exception {
            if (event instanceof MessageEvent) {
                    userLog.add(((MessageEvent) event).getMessage());
                    storage.sendOneWay(event);
            } 
//            else if (msg instanceof GetChatLog) {
//                    storage.forward(msg, getContext());
//            }

    }

}
