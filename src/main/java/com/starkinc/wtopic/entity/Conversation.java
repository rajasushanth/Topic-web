package com.starkinc.wtopic.entity;

import java.util.List;

public class Conversation {
	
	private String id;
	private String topicRef;
	private List<Message> messages;
	
	
	
	public String getId() {
		return id;
	}



	public void setId(String id) {
		this.id = id;
	}



	public String getTopicRef() {
		return topicRef;
	}



	public void setTopicRef(String topicRef) {
		this.topicRef = topicRef;
	}



	public List<Message> getMessages() {
		return messages;
	}



	public void setMessages(List<Message> messages) {
		this.messages = messages;
	}



	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Conversation [id=");
		builder.append(id);
		builder.append(", topicRef=");
		builder.append(topicRef);
		builder.append(", messageList=");
		builder.append(messages);
		builder.append("]");
		return builder.toString();
	}
	
	
}
