package br.com.insite.chat.actor;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import akka.actor.UntypedActor;
import akka.persistence.common.PersistentVector;
import akka.persistence.redis.RedisStorage;
import akka.stm.Atomic;
import br.com.insite.chat.event.MessageEvent;

public class RedisChatStorage extends UntypedActor {

	private static final Logger log = Logger.getLogger(RedisChatStorage.class);

	private final String CHAT_LOG = "akka.chat.log";

	private PersistentVector<byte[]> chatLog;

	public RedisChatStorage() {

		chatLog = RedisStorage.newVector(CHAT_LOG);
		log.info("Redis-based chat storage is starting up...");
	}

	public void onReceive(Object event) throws Exception {
		if (event instanceof MessageEvent) {
			final MessageEvent messageEvent = (MessageEvent) event;
			log.debug("New chat message [" + messageEvent.getMessage() + "]");

			new Atomic() {
				public Object atomically() {
					try {
						chatLog.add(messageEvent.getMessage().getBytes("UTF-8"));
					} catch (UnsupportedEncodingException e) {
						e.printStackTrace();
					}
					return null;
				}
			}.execute();

		}

		// else if (event instanceof GetChatLog) {
		// List<String> messageList = new Atomic<List<String>>() {
		// public List<String> atomically() {
		// List<String> messages = new ArrayList<String>();
		//
		// for (byte[] messageBytes : chatLog)
		// try {
		// messages.add(new String(messageBytes, "UTF-8"));
		// } catch (UnsupportedEncodingException e) {
		// e.printStackTrace();
		// }
		// return messages;
		// }
		// }.execute();
		// getContext().replyUnsafe(new ChatLog(messageList));
		// }

	}

	public void postRestart(Throwable reason) {
		chatLog = RedisStorage.newVector(CHAT_LOG);
	}

}
