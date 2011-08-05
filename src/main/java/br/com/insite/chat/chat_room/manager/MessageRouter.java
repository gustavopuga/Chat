package br.com.insite.chat.chat_room.manager;

import br.com.insite.chat.event.message.ChatMessageEvent;
import br.com.insite.chat.event.message.type.AprovedChatMessageEvent;
import br.com.insite.chat.event.message.type.PrivateChatMessageEvent;
import br.com.insite.chat.event.message.type.PublicChatMessageEvent;
import br.com.insite.chat.event.message.type.PublishNoticeEvent;
import br.com.insite.chat.model.user.ChatUser;
import br.com.insite.chat.model.user.Invited;
import br.com.insite.chat.model.user.Moderator;
import br.com.insite.chat.model.user.User;

public class MessageRouter implements ChatEventHandler<ChatMessageEvent> {

	private ChatSessionHandler sessionHandler;

	public MessageRouter(ChatSessionHandler sessionHandler) {
		this.sessionHandler = sessionHandler;
	}

	public void handleReceive(ChatMessageEvent event) {

		ChatUser from = event.getFrom();
		
		if (event.isPrivateMessage()) {
			ChatUser to = null;
			
			if (event instanceof PrivateChatMessageEvent) {

				if (from instanceof User) {
					to = sessionHandler.getModerator();
				} else if (from instanceof Moderator) {
					//TODO: Area de conversas privadas do convidado
					to = sessionHandler.getInvited();
				} else if (from instanceof Invited) {
					//TODO: Area de conversas privadas do moderador
				}
				
			}else if (event instanceof AprovedChatMessageEvent){
				to = sessionHandler.getInvited();
			}
			
			if (to != null){
				sessionHandler.getUserSession(from).sendOneWay(event);
				sessionHandler.getUserSession(to).sendOneWay(event);
			}

		} else {
			if (event instanceof PublicChatMessageEvent) {
				sessionHandler.getUserSession(from).sendOneWay(event);
				sessionHandler.getChatSession().sendOneWay(event);
			} else if (event instanceof PublishNoticeEvent) {
				sessionHandler.getUserSession(from).sendOneWay(event);
				sessionHandler.getNoticeSession().sendOneWay(event);
			}
		}

	}

}
