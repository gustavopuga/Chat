package br.com.insite.chat.user;

import static akka.actor.Actors.remote;
import static br.com.insite.chat.user.ChatServiceProperties.HOSTNAME;
import static br.com.insite.chat.user.ChatServiceProperties.PORT;
import static br.com.insite.chat.user.ChatServiceProperties.SERVICE_ID;
import akka.actor.ActorRef;
import br.com.insite.chat.event.LoginEvent;
import br.com.insite.chat.event.LogoutEvent;
import br.com.insite.chat.event.message.ChatMessageEvent;

public abstract class ChatUser {
	
	private String name;
	private ActorRef chat;

	protected ChatUser (String name) {
		
		this.name = name;
		
		// starts and connects the client to the remote server
		this.chat = remote().actorFor(SERVICE_ID, HOSTNAME, PORT);
	}

	public void login() {
		getChat().sendOneWay(new LoginEvent(this));
	}

	public void logout() {
		getChat().sendOneWay(new LogoutEvent(this));
	}

	public abstract void post(String message);
	
	protected void postMessageEvent(ChatMessageEvent event){
		getChat().sendOneWay(event);
	}

	protected String formatMessageAddUsername(String message) {
		return getName() + ": " + message;
	}
	
	protected String formatMessageRemoveUsername(String message) {
		
		int indexOf = message.indexOf(getName() + ": ");
		return message.substring(indexOf) ;
	}
	
	public ActorRef getChat() {
		return chat;
	}

	public String getName() {
		return name;
	}

	@Override
	public boolean equals(Object object) {
		
		if (object instanceof ChatUser) {
			ChatUser chatUser = (ChatUser) object;
			return this.getName().equals(chatUser.getName());
		}
		
		return false;
	}
}
