package br.com.insite.chat.chat_room.manager;

import br.com.insite.chat.event.ChatEvent;

public interface ChatEventHandler <T extends ChatEvent>{

	public abstract void handleReceive(T event);
	
}
