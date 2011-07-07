package br.com.insite.chat.event;

public class LogoutEvent extends ChatEvent {

	private static final long serialVersionUID = -2648348397423433176L;

	public LogoutEvent(String user) {
		super(user);
	}
	
	public String getUser() {
		return super.getUser();
	}
}
