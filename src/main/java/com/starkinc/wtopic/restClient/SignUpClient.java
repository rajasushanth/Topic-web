package com.starkinc.wtopic.restClient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.starkinc.wtopic.constants.Constants;
import com.starkinc.wtopic.entity.TopicUser;
import com.starkinc.wtopic.exception.ClientResponseException;
import com.starkinc.wtopic.exception.SignUpException;

@Service
public class SignUpClient {

	private RestTemplate restTemplate;
	private String baseURL;
	private String userResourcePath;

	public ResponseEntity<Object> signUp(TopicUser user) {
		final String saveuser = baseURL + userResourcePath;
		ResponseEntity<Object> resposneEntity = null;
		try {
			resposneEntity = restTemplate.postForEntity(saveuser, user, Object.class);
		} catch (ClientResponseException e) {
			throw new SignUpException(e);
		}
		return resposneEntity;
	}

	@Autowired
	public void setRestTemplate(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	@Value(Constants.BASE_URL)
	public void setUrl(String baseURL) {
		this.baseURL = baseURL;
	}

	@Value(Constants.USER_RESOURCE)
	public void setUserPath(String userResourcePath) {
		this.userResourcePath = userResourcePath;
	}

}
