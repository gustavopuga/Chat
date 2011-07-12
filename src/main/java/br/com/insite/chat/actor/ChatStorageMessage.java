package br.com.insite.chat.actor;

import java.io.UnsupportedEncodingException;

import org.apache.log4j.Logger;

import akka.actor.UntypedActor;
import akka.persistence.common.PersistentVector;
import akka.persistence.voldemort.VoldemortStorage;
import br.com.insite.chat.event.message.ChatMessageEvent;

public class ChatStorageMessage extends UntypedActor {

	private final String STORAGE_ID = "akka_chat";
	private static final Logger log = Logger.getLogger(ChatStorageMessage.class);

	private PersistentVector<byte[]> chatLog;

	public ChatStorageMessage() {

		chatLog = createNewVectorStorageMessage();
		log.info("O Servico de armazenamento basedo em vetores foi iniciado");
	}

	public void onReceive(Object event) throws Exception {
		
		if (event instanceof ChatMessageEvent) {
			
			ChatMessageEvent messageEvent = (ChatMessageEvent) event;
			String message = messageEvent.getMessage();
			log.debug("Nova mensagem de chat [" + message + "]");

			try {
				chatLog.add(message.getBytes("UTF-8"));
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
				log.error("Falha ao condificar a mensagem [" + message + "]", e);
			}

		}

	}

	public void postRestart(Throwable reason) {
		chatLog = createNewVectorStorageMessage();
	}

	private PersistentVector<byte[]> createNewVectorStorageMessage() {
		return VoldemortStorage.newVector(STORAGE_ID);
	}

}
