package com.starkinc.wtopic.restClient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.encrypt.TextEncryptor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.starkinc.wtopic.constants.Constants;
import com.starkinc.wtopic.entity.TopicUser;
import com.starkinc.wtopic.exception.TopicException;

@Service
public class LoginClient {
	
	private RestTemplate restTemplate;
	private String url;
	private String userPath;
	private TextEncryptor textEncryptor;
	
	public ResponseEntity<TopicUser[]> attemptLogin(String name, String password) {
		final String loginUrl = url + userPath; 
		TopicUser user = new TopicUser();
		user.setUsername(name);
		user.setPassword(textEncryptor.encrypt(password));
		ResponseEntity<TopicUser[]> resposneEntity = null;
		try {
			resposneEntity = restTemplate.postForEntity(loginUrl, user, TopicUser[].class);
		} catch (TopicException e) {
			resposneEntity = ResponseEntity
					.status(e.getHttpStatus())
					.header("errorMessage", e.getMessage())
					.body(null);
		}
		return resposneEntity; 
	}
	
	
	@Autowired
	public LoginClient(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}
	
	@Autowired
	public void setTextEncryptor(TextEncryptor textEncryptor) {
		this.textEncryptor = textEncryptor;
	}

	@Value(Constants.BASE_URL)
	public void setUrl(String url) {
		this.url = url;
	}

	@Value(Constants.LOGIN)
	public void setUserPath(String userPath) {
		this.userPath = userPath;
	}

}
