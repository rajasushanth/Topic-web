package com.starkinc.wtopic.entity;

public class TopicUser {
	
	private String username;
	
	private String password;
	
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
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("User [username=");
		builder.append(username);
		builder.append(", password=");
		builder.append(password);
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
