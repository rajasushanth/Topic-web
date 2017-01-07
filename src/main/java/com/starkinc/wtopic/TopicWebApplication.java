package com.starkinc.wtopic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;

import com.starkinc.wtopic.entity.User;
import com.starkinc.wtopic.restClient.LoginClient;

@SpringBootApplication
public class TopicWebApplication implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(TopicWebApplication.class, args);
	}
	
	@Autowired
	private LoginClient loginClient;

	@Override
	public void run(String... arg0) throws Exception {
		ResponseEntity<User[]> userEntity =  this.loginClient.getUserByName("sushneo");
		User user = userEntity.getBody()[0];
		System.out.println(user.toString());
	}
}
