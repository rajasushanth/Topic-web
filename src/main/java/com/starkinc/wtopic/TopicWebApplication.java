package com.starkinc.wtopic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TopicWebApplication {
	//implements CommandLineRunner{


	public static void main(String[] args) {
		SpringApplication.run(TopicWebApplication.class, args);
	}
	
	/*@Autowired
	private LoginClient loginClient;

	@Override
	public void run(String... arg0) throws Exception {
		ResponseEntity<User[]> userEntity =  this.loginClient.getUserByName("admin");
		User user = userEntity.getBody()[0];
		System.out.println(user.toString());
	}*/
}
