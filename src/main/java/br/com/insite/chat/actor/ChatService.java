package br.com.insite.chat.actor;

import static akka.actor.Actors.remote;

import org.apache.log4j.Logger;

import akka.actor.UntypedActor;
import akka.config.Supervision.FaultHandlingStrategy;
import akka.config.Supervision.OneForOneStrategy;
import br.com.insite.chat.actor.manager.ChatMessageHandler;
import br.com.insite.chat.actor.manager.InternalChatSessionHandler;
import br.com.insite.chat.user.ChatServiceProperties;

public class ChatService extends UntypedActor implements ChatServiceProperties{
	
	private static final int WITHIN_TIME_IN_MS = 1000;
	private static final int MAX_RESTART_RETRIES = 3;
	private static final Class[] EXECEPTIONS_TO_HANDLE = new Class[]{Throwable.class};
	private static final Logger log = Logger.getLogger(ChatService.class);
	
	private InternalChatSessionHandler internalChatSessionHandler;
	private ChatMessageHandler chatMessageHandler;

	public ChatService() {
		
		FaultHandlingStrategy faultHandlingStrategy = new OneForOneStrategy(EXECEPTIONS_TO_HANDLE, MAX_RESTART_RETRIES, WITHIN_TIME_IN_MS); 
		getContext().setFaultHandler(faultHandlingStrategy);
		
//		getContext().setLifeCycle(Supervision.permanent());
		
		internalChatSessionHandler = new InternalChatSessionHandler();
		chatMessageHandler = new ChatMessageHandler(internalChatSessionHandler);

		log.info("Iniciando o servidor do chat");
	}

	public void onReceive(Object event) {
		internalChatSessionHandler.handleReceive(event);
		chatMessageHandler.handleReceive(event);
	}
	
	public void preStart() {
        remote().start(HOSTNAME, PORT);
        remote().register(SERVICE_ID, getContext());
	}	
	
	public void postStop() {
		
		log.info("Finalizando o servidor do chat");
		internalChatSessionHandler.shutdownSessions();
	}

}
