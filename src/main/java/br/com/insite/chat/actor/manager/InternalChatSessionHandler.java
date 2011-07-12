package br.com.insite.chat.actor.manager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.management.monitor.Monitor;

import org.apache.log4j.Logger;

import akka.actor.ActorRef;
import akka.actor.Actors;
import br.com.insite.chat.actor.factory.InternalChatSessionFactory;
import br.com.insite.chat.event.LoginEvent;
import br.com.insite.chat.event.LogoutEvent;
import br.com.insite.chat.user.ChatUser;
import br.com.insite.chat.user.Interviewed;

public class InternalChatSessionHandler {

	private static final Logger log = Logger.getLogger(InternalChatSessionHandler.class);
    
	private Map<ChatUser, ActorRef> sessions;
    private List<Monitor> monitors;
    private List<Interviewed> intervieweds;
    
    public InternalChatSessionHandler() {
        this.sessions = new HashMap<ChatUser, ActorRef>();
        this.monitors = new ArrayList<Monitor>();
        this.intervieweds = new ArrayList<Interviewed>();
    }

    public ActorRef getSession(ChatUser chatUser) {
        return sessions.get(chatUser);
    }

    public void handleReceive(Object event) {
        
    	if (event instanceof LoginEvent) {
            
    		LoginEvent loginEvent = (LoginEvent) event;
			ChatUser chatUser = loginEvent.getUser();
			String username = chatUser.getName();
			
            ActorRef session = Actors.actorOf(new InternalChatSessionFactory(username));
            session.start();
            
            sessions.put(chatUser, session);
            log.info("Usuario [" + username + "] foi conectado ao sistema");

        } 
    	
    	else if (event instanceof LogoutEvent) {
        	
            LogoutEvent logoutEvent = (LogoutEvent) event;
            ChatUser chatUser = logoutEvent.getUser();
            
            ActorRef session = sessions.get(chatUser);
            session.stop();
            
            sessions.remove(chatUser);
            log.info("Usuario [" + chatUser.getName() + "] foi desconectado do sistema");
        }
    }

    public void shutdownSessions() {
        for (ActorRef session : sessions.values())
            session.stop();
    }
    
}
