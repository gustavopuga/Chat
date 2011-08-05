package br.com.insite.chat.chat_room;

import static akka.actor.Actors.remote;

import org.apache.log4j.Logger;

import akka.actor.UntypedActor;
import akka.config.Supervision.FaultHandlingStrategy;
import akka.config.Supervision.OneForOneStrategy;
import br.com.insite.chat.chat_room.manager.MessageRouter;
import br.com.insite.chat.chat_room.manager.ChatSessionHandler;
import br.com.insite.chat.chat_room.property.ChatRoomProperties;
import br.com.insite.chat.event.message.ChatMessageEvent;
import br.com.insite.chat.event.registration.RegistrationChatEvent;

public class ChatRoom extends UntypedActor implements ChatRoomProperties{
	
	private static final int WITHIN_TIME_IN_MS = 1000;
	private static final int MAX_RESTART_RETRIES = 3;
	private static final Class[] EXECEPTIONS_TO_HANDLE = new Class[]{Throwable.class};
	private static final Logger log = Logger.getLogger(ChatRoom.class);
	
	private ChatSessionHandler chatSessionHandler;
	private MessageRouter messageRouter;

	public ChatRoom() {
		
		FaultHandlingStrategy faultHandlingStrategy = new OneForOneStrategy(EXECEPTIONS_TO_HANDLE, MAX_RESTART_RETRIES, WITHIN_TIME_IN_MS); 
		getContext().setFaultHandler(faultHandlingStrategy);
		
//		getContext().setLifeCycle(Supervision.permanent());
		
		chatSessionHandler = new ChatSessionHandler();
		messageRouter = new MessageRouter(chatSessionHandler);

		log.info("Iniciando o servidor do chat");
	}

	public void onReceive(Object event) {
		if (event instanceof ChatMessageEvent){
			messageRouter.handleReceive((ChatMessageEvent) event);
		}else if (event instanceof RegistrationChatEvent){
			chatSessionHandler.handleReceive((RegistrationChatEvent) event);
		}
	}
	
	public void preStart() {
        remote().start(HOSTNAME, PORT);
        remote().register(SERVICE_ID, getContext());
	}	
	
	public void postStop() {
		
		log.info("Finalizando o servidor do chat");
		chatSessionHandler.shutdownSessions();
	}

}
