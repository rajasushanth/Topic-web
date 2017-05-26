package com.starkinc.wtopic.serviceImpl;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.HttpStatus.FOUND;

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
		ResponseEntity<Topic> topicEntity = topicClient.updateTopic(topicName, firstMessage);
		Topic topicResponse = null;
		if(null != topicEntity && topicEntity.getStatusCode() == OK){
			topicResponse = topicEntity.getBody();
		}
		return topicResponse;
	}
	
	@Override
	public Topic getTopicByName(String topicname) {
		ResponseEntity<Topic> topicEntity =  topicClient.getTopicByName(topicname);
		Topic topicResponse = null;
		if(null != topicEntity && topicEntity.getStatusCode() == FOUND){
			topicResponse = topicEntity.getBody();
		}
		return topicResponse;
	}

	@Autowired
	public void setTopicClient(TopicClient topicClient) {
		this.topicClient = topicClient;
	}
	

}
