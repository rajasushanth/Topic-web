package com.starkinc.wtopic.restClient;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.security.crypto.encrypt.TextEncryptor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.starkinc.wtopic.constants.Constants;
import com.starkinc.wtopic.entity.TopicUser;

@Service
public class LoginClient {
	
	private RestTemplate restTemplate;
	private String url;
	private String userPath;
	private List<ClientHttpRequestInterceptor> interceptorList;
	private TextEncryptor textEncryptor;
	
	public ResponseEntity<TopicUser[]> attemptLogin(String name, String password) {
		final String searchUserUrl = url + userPath; 
		restTemplate.setInterceptors(interceptorList);
		TopicUser user = new TopicUser();
		user.setUsername(name);
		user.setPassword(textEncryptor.encrypt(password));
		return restTemplate.postForEntity(searchUserUrl, user, TopicUser[].class); 
	}
	
	
	@Autowired
	public LoginClient(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}
	
	@Autowired
	public void setInterceptorList(List<ClientHttpRequestInterceptor> interceptorList) {
		this.interceptorList = interceptorList;
	}
	
	@Autowired
	public void setTextEncryptor(TextEncryptor textEncryptor) {
		this.textEncryptor = textEncryptor;
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
