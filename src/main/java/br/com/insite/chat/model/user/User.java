package br.com.insite.chat.model.user;

public class User extends ChatUser{

	private boolean visitant;
	
	public User(String name) {
		super(name);
	}

	public void post(String message) {
		if(isVisitant()){
//			sendEvent(new ParticipantMessageEvent(this, message));
		}
	}

	public void setVisitant(boolean visitant) {
		this.visitant = visitant;
	}

	public boolean isVisitant() {
		return visitant;
	}

	@Override
	public void onReceive(Object event) throws Exception {
		// TODO Auto-generated method stub
		
	}

}
