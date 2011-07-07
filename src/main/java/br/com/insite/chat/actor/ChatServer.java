package br.com.insite.chat.actor;

import org.apache.log4j.Logger;

import akka.actor.ActorRef;
import akka.actor.UntypedActor;
import akka.config.Supervision;
import br.com.insite.chat.actor.manager.ChatManagement;
import br.com.insite.chat.actor.manager.SessionManagement;


public class ChatServer extends UntypedActor {
	
	private static final Logger log = Logger.getLogger(ChatServer.class);
	
	private ActorRef storage;
	private SessionManagement sessionManager;
	private ChatManagement chatManager;

	public ChatServer() {
		// Creates and links a RedisChatStorage
		storage = getContext().spawnLink(RedisChatStorage.class);
		
		Supervision.FaultHandlingStrategy faultHandler = new Supervision.OneForOneStrategy(
				null, // exceptions to handle
				null, // max restart retries
				null); // within time in ms
		getContext().setFaultHandler(faultHandler);

		sessionManager = new SessionManagement(getContext(), storage);
		chatManager = new ChatManagement(getContext(), sessionManager);

		log.info("Iniciando o servidor do chat");
	}

	public void onReceive(final Object event) {
		sessionManager.handleReceive(event);
		chatManager.handleReceive(event);
	}

	public void postStop() {
		
		log.info("Finalizando o servidor do chat");
		
		sessionManager.shutdownSessions();
		getContext().unlink(storage);
		storage.stop();
	}

}
