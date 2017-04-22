package com.starkinc.wtopic.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import static org.springframework.http.HttpStatus.OK;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.starkinc.wtopic.entity.TopicUser;
import com.starkinc.wtopic.restClient.LoginClient;
import com.starkinc.wtopic.service.LoginService;

@Service
public class LoginServiceImpl implements LoginService{
	
	private LoginClient loginClient;

	@Override
	public boolean validateLogin(String name, String password) {
		boolean isAuthenticated = false; 
		ResponseEntity<TopicUser[]> userEntity = loginClient.attemptLogin(name, password);
		HttpStatus statusCode = userEntity.getStatusCode();
		if(statusCode == OK){
			isAuthenticated = true;
			HttpHeaders headers =  userEntity.getHeaders();
			List<String> authorization = headers.get("Authorization");
			System.out.println(authorization.get(0));
		}
		return isAuthenticated;
	}

	@Autowired
	public void setLoginClient(LoginClient loginClient) {
		this.loginClient = loginClient;
	}
	
}
