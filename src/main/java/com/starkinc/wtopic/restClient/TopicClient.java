package com.starkinc.wtopic.restClient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.starkinc.wtopic.constants.Constants;
import com.starkinc.wtopic.entity.Topic;
import com.starkinc.wtopic.entity.UserSession;
import com.starkinc.wtopic.exception.ClientResponseException;
import com.starkinc.wtopic.exception.TopicException;

@Service
public class TopicClient {
	
	private RestTemplate restTemplate;
	private String baseURL;
	private String topicResourcePath;
	
	
	public ResponseEntity<Topic> createTopic(Topic topic, UserSession userSession){
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
		headers.add("Authorization", userSession.getToken());
		topic.setAuthor(userSession.getUsername());
		HttpEntity<Topic> entity = new HttpEntity<Topic>(topic, headers);
		try{
			
		}catch (ClientResponseException e) {
			throw new TopicException(e);
		}
		return restTemplate.postForEntity(baseURL + topicResourcePath, entity, Topic.class);
	}
	
	@Autowired
	public void setRestTemplate(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}
	
	@Value(Constants.BASE_URL)
	public void setBaseURL(String baseURL) {
		this.baseURL = baseURL;
	}
	
	@Value(Constants.TOPIC_RESOURCE)
	public void setPath(String topicResourcePath) {
		this.topicResourcePath = topicResourcePath;
	}
	
	
	

}
