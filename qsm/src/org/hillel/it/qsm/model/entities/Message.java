package org.hillel.it.qsm.model.entities;

public class Message extends BaseEntity{
	private String theme;
	private String senderMail;
	private String recieverMail;
	private String text;
	public Message(String theme, String senderMail, String recieverMail, String text) {
		this.theme = theme;
		this.senderMail = senderMail;
		this.recieverMail = recieverMail;
		this.text = text;
	}
	public String getTheme() {
		return theme;
	}
	public void setTheme(String theme) {
		this.theme = theme;
	}
	public String getSenderMail() {
		return senderMail;
	}
	public void setSenderMail(String senderMail) {
		this.senderMail = senderMail;
	}
	public String getRecieverMail() {
		return recieverMail;
	}
	public void setRecieverMail(String recieverMail) {
		this.recieverMail = recieverMail;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	

	


	
	
}
