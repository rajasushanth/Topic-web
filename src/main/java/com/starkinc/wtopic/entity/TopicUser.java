package com.starkinc.wtopic.entity;

public class TopicUser {
	
	private String username;
	
	private String password;
	
	private String question;
	
	private String answer;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("TopicUser [username=");
		builder.append(username);
		builder.append(", password=");
		builder.append(password);
		builder.append(", question=");
		builder.append(question);
		builder.append(", answer=");
		builder.append(answer);
		builder.append("]");
		return builder.toString();
	}
	public TopicUser(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}
	public TopicUser() {
		super();
	}
	
	
}
