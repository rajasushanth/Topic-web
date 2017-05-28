package com.starkinc.wtopic.service;

import java.util.List;

import com.starkinc.wtopic.entity.Topic;

public interface TopicService {
	
	public Topic createTopic(Topic topic);
	public Topic updateTopic(String topicName, String firstMessage);
	public Topic getTopicByName(String topicname);
	public List<String> getTopicsByAuthor(int skip);

}
