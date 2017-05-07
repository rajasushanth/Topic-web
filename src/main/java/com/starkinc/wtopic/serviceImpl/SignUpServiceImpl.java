package com.starkinc.wtopic.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import static org.springframework.http.HttpStatus.CREATED;
import org.springframework.http.ResponseEntity;

import com.starkinc.wtopic.constants.Constants;
import com.starkinc.wtopic.entity.TopicUser;
import com.starkinc.wtopic.restClient.SignUpClient;
import com.starkinc.wtopic.service.SignUpService;

public class SignUpServiceImpl implements SignUpService {
	
	private SignUpClient signUpClient;
	private String headerPrefix;

	@Override
	public String signUp(TopicUser user) {
		String token = null;
		ResponseEntity<Object> entity = signUpClient.signUp(user);
		if(entity.getStatusCode() == CREATED){
			token = entity.getHeaders().getFirst(headerPrefix);
		}else if(true){
			
		}
		return token;
	}
	
	@Autowired
	public void setSignUpClient(SignUpClient signUpClient) {
		this.signUpClient = signUpClient;
	}
	
	@Value(Constants.HEADER_STRING)
	public void setHeaderPrefix(String headerPrefix) {
		this.headerPrefix = headerPrefix;
	}
	
	

}
