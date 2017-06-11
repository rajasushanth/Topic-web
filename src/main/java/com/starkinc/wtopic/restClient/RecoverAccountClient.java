package com.starkinc.wtopic.restClient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.starkinc.wtopic.constants.Constants;
import com.starkinc.wtopic.dto.ResetPasswordDTO;
import com.starkinc.wtopic.entity.TopicUser;
import com.starkinc.wtopic.exception.ClientResponseException;
import com.starkinc.wtopic.exception.RecoverException;
import com.starkinc.wtopic.util.TopicWebUtils;

@Service
public class RecoverAccountClient {

	private RestTemplate restTemplate;
	private String baseURL;
	private String userResourcePath;

	public ResponseEntity<TopicUser> getSecurityQuestion(String username) {
		final String recoverURL = TopicWebUtils.appendPath(baseURL + userResourcePath, username);
		ResponseEntity<TopicUser> resposneEntity = null;
		try {
			resposneEntity = restTemplate.getForEntity(recoverURL, TopicUser.class);
		} catch (ClientResponseException e) {
			throw new RecoverException(e);
		}
		return resposneEntity;
	}
	
	public ResponseEntity<Boolean> recoverAccount(String username, ResetPasswordDTO resetPasswordDTO){
		final String recoverURL = TopicWebUtils.appendPath(baseURL + userResourcePath, username);
		ResponseEntity<Boolean> entity = null;
		entity = restTemplate.postForEntity(recoverURL, resetPasswordDTO, Boolean.class);
		return entity;
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
