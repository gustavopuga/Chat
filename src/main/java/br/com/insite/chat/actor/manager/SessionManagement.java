package br.com.insite.chat.actor.manager;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import akka.actor.ActorRef;
import akka.actor.Actors;
import akka.actor.UntypedActor;
import akka.actor.UntypedActorFactory;
import br.com.insite.chat.actor.InternalChatSession;
import br.com.insite.chat.event.LoginEvent;
import br.com.insite.chat.event.LogoutEvent;

public class SessionManagement {

	private static final Logger log = Logger.getLogger(SessionManagement.class);
	
	private ActorRef self;
    private ActorRef storage;
    private Map<String, ActorRef> sessions;

    public SessionManagement(ActorRef self, ActorRef storage) {
        this.self = self;
        this.storage = storage;
        this.sessions = new HashMap<String, ActorRef>();
    }

    public ActorRef getSession(String username) {
        return sessions.get(username);
    }

    public void handleReceive(final Object event) {
        if (event instanceof LoginEvent) {
            LoginEvent loginEvent = (LoginEvent) event;
            
			final String username = loginEvent.getUser();
            ActorRef session = Actors.actorOf(new UntypedActorFactory() {
                public UntypedActor create() {
                    return new InternalChatSession(username, storage);
                }
            });
            
            session.start();
            sessions.put(loginEvent.getUser(), session);
            
            log.info("Usuario [" + username + "] foi conectado ao sistema");

        } else if (event instanceof LogoutEvent) {
        	
            LogoutEvent logoutEvent = (LogoutEvent) event;
			
            String username = logoutEvent.getUser();
            ActorRef session = sessions.get(username);
            session.stop();
            
            sessions.remove(username);
            log.info("Usuario [" + username + "] foi desconectado do sistema");
        }
    }

    public void shutdownSessions() {
        for (ActorRef session : sessions.values())
            session.stop();
    }
    
}
