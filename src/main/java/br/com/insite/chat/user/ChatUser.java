package br.com.insite.chat.user;

import static akka.actor.Actors.remote;
import akka.actor.ActorRef;
import akka.actor.Actors;

public abstract class ChatUser {
	
	private final String SERVICE_ID = "chat:service";
	private final String HOSTNAME = "localhost";
	private final int PORT = 2552;
	
	private String name = null;
	private ActorRef chat = null;

	protected ChatUser (String name) {
		
		this.name = name;
		
		// starts and connects the client to the remote server
		this.chat = remote().actorFor(SERVICE_ID, HOSTNAME, PORT);
	}

	public void login() {
//		chat.sendOneWay(new Login(name));
	}

	public void logout() {
//		chat.sendOneWay(new Logout(name));
	}

	public abstract void post(String message);
//		chat.sendOneWay(new ChatMessage(name, name + ": " + message));
	
}
