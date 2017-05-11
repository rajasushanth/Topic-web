package com.starkinc.wtopic.restClient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.starkinc.wtopic.constants.Constants;
import com.starkinc.wtopic.entity.TopicUser;
import com.starkinc.wtopic.exception.TopicException;

@Service
public class SignUpClient {
	
	private RestTemplate restTemplate;
	private String url;
	private String userPath;
	
	public ResponseEntity<Object> signUp(TopicUser user){
		final String saveuser = url + userPath;
		ResponseEntity<Object> resposneEntity = null;
		try{
			resposneEntity = restTemplate.postForEntity(saveuser, user, Object.class);
		}catch (TopicException e) {
			resposneEntity = ResponseEntity
					.status(e.getHttpStatus())
					.header("signUpErrorMessage", e.getMessage())
					.body(null);
		}
		return resposneEntity;
	}
	
	@Autowired
	public void setRestTemplate(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}
	
	@Value(Constants.BASE_URL)
	public void setUrl(String url) {
		this.url = url;
	}

	@Value(Constants.USER_RESOURCE)
	public void setUserPath(String userPath) {
		this.userPath = userPath;
	}
	
	
	

}
