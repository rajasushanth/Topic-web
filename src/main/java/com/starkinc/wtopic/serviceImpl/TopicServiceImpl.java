package com.starkinc.wtopic.serviceImpl;

import static org.springframework.http.HttpStatus.CREATED;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.starkinc.wtopic.entity.Topic;
import com.starkinc.wtopic.restClient.TopicClient;
import com.starkinc.wtopic.service.TopicService;

@Service
public class TopicServiceImpl implements TopicService {
	
	private TopicClient topicClient;

	@Override
	public Topic createTopic(Topic topic) {
		ResponseEntity<Topic> topicEntity = topicClient.createTopic(topic);
		Topic topicResponse = null;
		if(null != topicEntity && topicEntity.getStatusCode() == CREATED){
			topicResponse = topicEntity.getBody();
		}
		return topicResponse;
	}

	@Override
	public Topic updateTopic(String topicName, String firstMessage) {
		topicClient.updateTopic(topicName, firstMessage);
		return null;
	}
	
	@Autowired
	public void setTopicClient(TopicClient topicClient) {
		this.topicClient = topicClient;
	}

	
	

}
