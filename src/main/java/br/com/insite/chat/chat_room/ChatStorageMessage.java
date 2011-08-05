package br.com.insite.chat.chat_room;

import java.io.UnsupportedEncodingException;
import java.util.List;

import org.apache.log4j.Logger;

import akka.actor.UntypedActor;
import akka.persistence.cassandra.CassandraStorage;
import akka.persistence.common.PersistentVector;
import br.com.insite.chat.event.message.ComplexMessageEvent;
import br.com.insite.chat.event.message.SimpleMessageEvent;

public class ChatStorageMessage extends UntypedActor {

	private static final Logger log = Logger.getLogger(ChatStorageMessage.class);

	private PersistentVector<byte[]> chatLog;

	public ChatStorageMessage() {

		chatLog = createNewVectorStorageMessage();
		log.info("O Servico de armazenamento basedo em vetores foi iniciado");
	}

	public void onReceive(Object event) {
		
		if (event instanceof SimpleMessageEvent) {
			
			SimpleMessageEvent messageEvent = (SimpleMessageEvent) event;
			String message = messageEvent.getMessage();
			storageMessage(message);
		}
		else if (event instanceof ComplexMessageEvent) {
			
			ComplexMessageEvent messageEvent = (ComplexMessageEvent) event;
			List<String> messages = messageEvent.getMessages();

			for (String message : messages) {		
				storageMessage(message);
			}
		}

	}

	private void storageMessage(String message) {
		
		log.debug("Nova mensagem de chat [" + message + "]");
		
		try {
			chatLog.add(message.getBytes("UTF-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			log.error("Falha ao condificar a mensagem [" + message + "]", e);
		}
		
	}

	public void postRestart(Throwable reason) {
		chatLog = createNewVectorStorageMessage();
	}

	private PersistentVector<byte[]> createNewVectorStorageMessage() {
		return CassandraStorage.newVector();
	}

}
