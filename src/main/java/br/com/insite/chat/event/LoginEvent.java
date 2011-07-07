package br.com.insite.chat.event;

public class LoginEvent extends ChatEvent{

	private static final long serialVersionUID = 4321543103502198715L;

	public LoginEvent(String user) {
		super(user);
	}
	
	public String getUser() {
		return super.getUser();
	}
}
