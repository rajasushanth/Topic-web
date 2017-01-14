package com.starkinc.wtopic.restClient;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.starkinc.wtopic.constants.Constants;
import com.starkinc.wtopic.entity.User;

@Service
public class LoginClient {
	
	private RestTemplate restTemplate;
	private String url;
	private String userPath;
	private String searchPath;
	private List<ClientHttpRequestInterceptor> interceptorList;
	
	public ResponseEntity<User[]> getUserByName(String name){
		Map<String, String> params = new HashMap<>();
		params.put(Constants.USERNAME, name);
		final String searchUserUrl = url + userPath + searchPath; 
		restTemplate.setInterceptors(interceptorList);
		return restTemplate.getForEntity(searchUserUrl, User[].class, params);
	}
	
	
	@Autowired
	public LoginClient(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}
	
	@Autowired
	public void setInterceptorList(List<ClientHttpRequestInterceptor> interceptorList) {
		this.interceptorList = interceptorList;
	}

	@Value(Constants.REST_ROOT_URL)
	public void setUrl(String url) {
		this.url = url;
	}

	@Value(Constants.USER_CLIENT)
	public void setUserPath(String userPath) {
		this.userPath = userPath;
	}

	@Value(Constants.USER_SEARCH)
	public void setSearchPath(String searchPath) {
		this.searchPath = searchPath;
	}
	
}
