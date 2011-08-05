package br.com.insite.chat.chat_room.manager;

import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import org.apache.log4j.Logger;

import akka.actor.ActorRef;
import akka.actor.Actors;
import br.com.insite.chat.chat_room.factory.ChatSessionFactory;
import br.com.insite.chat.event.registration.LoginEvent;
import br.com.insite.chat.event.registration.LogoutEvent;
import br.com.insite.chat.event.registration.RegistrationChatEvent;
import br.com.insite.chat.model.user.ChatUser;
import br.com.insite.chat.model.user.Invited;
import br.com.insite.chat.model.user.Moderator;

public class ChatSessionHandler implements ChatEventHandler<RegistrationChatEvent>{

	private static final Logger log = Logger.getLogger(ChatSessionHandler.class);
    
	private Map<ChatUser, ActorRef> userSessions;
	private List<ChatUser> moderators;
	
	private ActorRef chatSession;
	private ActorRef noticeSession;
	
	private ChatUser invitedSession;
    
    public ChatSessionHandler() {
        this.userSessions = new Hashtable<ChatUser, ActorRef>();
        this.moderators = new Vector<ChatUser>();
        
        chatSession = Actors.actorOf(new ChatSessionFactory());
        noticeSession = Actors.actorOf(new ChatSessionFactory());
    }

    public ActorRef getUserSession(ChatUser chatUser) {
        return userSessions.get(chatUser);
    }

    public void handleReceive(RegistrationChatEvent event) {
        
    	if (event instanceof LoginEvent) {
            
    		LoginEvent loginEvent = (LoginEvent) event;
			ChatUser chatUser = loginEvent.getUser();
			String username = chatUser.getName();
			
            ActorRef session = Actors.actorOf(new ChatSessionFactory(username));
            session.start();
            
            userSessions.put(chatUser, session);
            if (chatUser instanceof Moderator){
            	moderators.add(0, chatUser);
            } else if (chatUser instanceof Invited){
            	invitedSession = chatUser;
            }
            
            log.info("Usuario [" + username + "] foi conectado ao sistema");

        } 
    	
    	else if (event instanceof LogoutEvent) {
        	
            LogoutEvent logoutEvent = (LogoutEvent) event;
            ChatUser chatUser = logoutEvent.getUser();
            
            ActorRef session = userSessions.get(chatUser);
            session.stop();
            
            userSessions.remove(chatUser);
            log.info("Usuario [" + chatUser.getName() + "] foi desconectado do sistema");
        }
    }

    public void shutdownSessions() {
        for (ActorRef session : userSessions.values())
            session.stop();
    }

    public ChatUser getModerator() {
		
    	ChatUser moderatorSession = moderators.remove(0);
    	moderators.add(moderatorSession);
    	return moderatorSession;
	}
    
	public ChatUser getInvited() {
		return invitedSession;
	}

	public ActorRef getChatSession() {
		return chatSession;
	}

	public ActorRef getNoticeSession() {
		return noticeSession;
	}
    
}
