package com.starkinc.wtopic.dto;

public class SearchDTO {
	
	private String author;
	private String topicName;
	private TopicsDTO topicsDTO;
	
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getTopicName() {
		return topicName;
	}
	public void setTopicName(String topicName) {
		this.topicName = topicName;
	}
	public TopicsDTO getTopicsDTO() {
		return topicsDTO;
	}
	public void setTopicsDTO(TopicsDTO topicsDTO) {
		this.topicsDTO = topicsDTO;
	}
	public SearchDTO(String author, String topicName) {
		super();
		this.author = author;
		this.topicName = topicName;
	}
	public SearchDTO() {
	}
	
	
}
