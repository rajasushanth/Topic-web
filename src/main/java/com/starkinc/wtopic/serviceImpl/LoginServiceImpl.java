package com.starkinc.wtopic.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.starkinc.wtopic.entity.User;
import com.starkinc.wtopic.restClient.LoginClient;
import com.starkinc.wtopic.service.LoginService;

@Service
public class LoginServiceImpl implements LoginService{
	
	private LoginClient loginClient;

	@Override
	public boolean validateLogin(String name, String password) {
		boolean isAuthenticated = false; 
		ResponseEntity<User[]> userEntity = loginClient.getUserByName(name);
		HttpStatus statusCode = userEntity.getStatusCode();
		if(statusCode == HttpStatus.OK){
			if(null != userEntity.getBody() && null != userEntity.getBody()[0]){
				User user = userEntity.getBody()[0];
				if(password.equals(user.getPassword())){
					isAuthenticated = true;
				}
			}
		}
		return isAuthenticated;
	}

	@Autowired
	public void setLoginClient(LoginClient loginClient) {
		this.loginClient = loginClient;
	}
	
}
