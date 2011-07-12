package br.com.insite.chat.actor.manager;

import br.com.insite.chat.event.message.ChatMessageEvent;
import br.com.insite.chat.user.ChatUser;

public class ChatMessageHandler {

	private InternalChatSessionHandler sessionHandler;

	public ChatMessageHandler(InternalChatSessionHandler sessionMgr) {
		this.sessionHandler = sessionMgr;
	}

	public void handleReceive(Object event) {
		if (event instanceof ChatMessageEvent) {
			
			ChatMessageEvent messageEvent = (ChatMessageEvent) event;
			ChatUser form = messageEvent.getFrom();
			ChatUser to = messageEvent.getTo();
			
			sessionHandler.getSession(form).sendOneWay(event);
			sessionHandler.getSession(to).sendOneWay(event);
		}
	}
}
