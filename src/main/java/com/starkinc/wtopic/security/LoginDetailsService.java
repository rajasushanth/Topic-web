package com.starkinc.wtopic.security;

import static org.springframework.http.HttpStatus.OK;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.starkinc.wtopic.constants.Constants;
import com.starkinc.wtopic.entity.TopicUser;
import com.starkinc.wtopic.restClient.LoginClient;

@Service
public class LoginDetailsService implements UserDetailsService {
	
	private LoginClient loginClient;

	@Override
	public UserDetails loadUserByUsername(String usernameWithPassord) throws UsernameNotFoundException {
		String[] splitted = usernameWithPassord.split("~");
		String userName = splitted[0], password = splitted[1];
		ResponseEntity<TopicUser[]> userEntity = loginClient.attemptLogin(userName, password);
		HttpStatus statusCode = userEntity.getStatusCode();
		if(statusCode == OK){
			HttpHeaders headers =  userEntity.getHeaders();
			List<String> authorization = headers.get("Authorization");
			System.out.println(authorization.get(0));
			
			List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
			authorities.add(new SimpleGrantedAuthority(Constants.ROLE_USER));
			return new User(usernameWithPassord, Constants.PASSWORD_PLACEHOLDER, true, true, true, true, authorities);
		}

		throw new UsernameNotFoundException("User "+ userName + " not found");
	}
	
	@Autowired
	public void setLoginClient(LoginClient loginClient) {
		this.loginClient = loginClient;
	}

}
