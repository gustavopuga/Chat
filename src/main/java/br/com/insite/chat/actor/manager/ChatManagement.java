package br.com.insite.chat.actor.manager;

import akka.actor.ActorRef;
import br.com.insite.chat.event.MessageEvent;

public class ChatManagement {
	
	 private ActorRef self;
     private SessionManagement sessionManager;

     public ChatManagement(ActorRef self, SessionManagement sessionMgr) {
         this.self = self;
         this.sessionManager = sessionMgr;
     }

     public void handleReceive(Object event) {
         if (event instanceof MessageEvent){
             MessageEvent messageEvent = (MessageEvent) event;
			sessionManager.getSession(messageEvent.getFrom()).sendOneWay(event);
         }
//         else if (msg instanceof GetChatLog)
//             sessionMgr.getSession(((GetChatLog) msg).getFrom()).forward(
//                     msg, self);
     }
}
