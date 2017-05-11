package com.starkinc.wtopic.serviceImpl;

import static org.springframework.http.HttpStatus.CREATED;

import java.util.HashMap;
import java.util.Map;

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
	public Map<String, String> signUp(TopicUser user) {
		String token = null;
		String encryptedPassword = textEncryptor.encrypt(user.getPassword());
		user.setPassword(encryptedPassword);
		ResponseEntity<Object> entity = signUpClient.signUp(user);
		Map<String, String> responseMap = new HashMap<>();
		if(entity.getStatusCode() == CREATED){
			token = entity.getHeaders().getFirst(headerPrefix);
			responseMap.put("token", token);
		}else {
			responseMap.put("error", entity.getHeaders().getFirst("signUpErrorMessage"));
		}
		return responseMap;
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
