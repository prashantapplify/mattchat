package com.chatapp.model;

public class Get_convercation_model {

	
	String senderid;
	String sendername;
	String chat;
	String Chat_Type;
	String chatid;
	String time;
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getChatid() {
		return chatid;
	}
	public void setChatid(String chatid) {
		this.chatid = chatid;
	}
	public String getChat_Type() {
		return Chat_Type;
	}
	public void setChat_Type(String chat_Type) {
		Chat_Type = chat_Type;
	}
	public String getSenderid() {
		return senderid;
	}
	public void setSenderid(String senderid) {
		this.senderid = senderid;
	}
	public String getSendername() {
		return sendername;
	}
	public void setSendername(String sendername) {
		this.sendername = sendername;
	}
	public String getChat() {
		return chat;
	}
	public void setChat(String chat) {
		this.chat = chat;
	}
}
