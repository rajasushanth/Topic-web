package com.starkinc.wtopic.restClient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.starkinc.wtopic.entity.Topic;

@Service
public class TopicClient {
	
	private RestTemplate restTemplate;
	private String baseURL;
	private String path;
	
	
	public ResponseEntity<Topic> createTopic(Topic topic){
		restTemplate.postForEntity(baseURL + path, topic, Topic.class);
		
		return null;
	}
	
	@Autowired
	public void setRestTemplate(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}
	
	@Value("")
	public void setBaseURL(String baseURL) {
		this.baseURL = baseURL;
	}
	
	@Value("")
	public void setPath(String path) {
		this.path = path;
	}
	
	
	

}
