package com.starkinc.wtopic.serviceImpl;

import static org.springframework.http.HttpStatus.CREATED;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.encrypt.TextEncryptor;
import org.springframework.stereotype.Service;

import com.starkinc.wtopic.constants.Constants;
import com.starkinc.wtopic.entity.TopicUser;
import com.starkinc.wtopic.restClient.SignUpClient;
import com.starkinc.wtopic.service.SignUpService;

@Service
public class SignUpServiceImpl implements SignUpService {
	
	private SignUpClient signUpClient;
	private String headerPrefix;
	private TextEncryptor textEncryptor;

	@Override
	public String signUp(TopicUser user) {
		String token = null;
		String encryptedPassword = textEncryptor.encrypt(user.getPassword());
		user.setPassword(encryptedPassword);
		ResponseEntity<Object> entity = signUpClient.signUp(user);
		if(entity.getStatusCode() == CREATED){
			token = entity.getHeaders().getFirst(headerPrefix);
		}
		return token;
	}
	
	@Autowired
	public void setSignUpClient(SignUpClient signUpClient) {
		this.signUpClient = signUpClient;
	}
	
	@Autowired
	public void setTextEncryptor(TextEncryptor textEncryptor) {
		this.textEncryptor = textEncryptor;
	}

	@Value(Constants.HEADER_STRING)
	public void setHeaderPrefix(String headerPrefix) {
		this.headerPrefix = headerPrefix;
	}
	
	

}
