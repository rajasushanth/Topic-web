package com.starkinc.wtopic.service;

import com.starkinc.wtopic.entity.Topic;

public interface TopicService {
	
	public Topic createTopic(Topic topic);
	public Topic updateTopic(String topicName, String firstMessage);

}
