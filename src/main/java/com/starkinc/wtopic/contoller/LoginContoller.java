package com.starkinc.wtopic.contoller;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class LoginContoller {
	
	@GetMapping("/login")
	public String login(){
		return null;
	}
	
	@GetMapping("/index")
	public String index(){
		return "index";
	}

}
