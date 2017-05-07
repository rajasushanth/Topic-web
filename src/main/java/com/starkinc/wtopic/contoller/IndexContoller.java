package com.starkinc.wtopic.contoller;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import static org.springframework.web.bind.annotation.RequestMethod.POST;;

@Controller
@RequestMapping(produces = APPLICATION_JSON_VALUE)
public class IndexContoller {
	
	
	@RequestMapping("/")
	public String index(){
		return "index";
	}
	
	@RequestMapping("/home")
	public String home(){
		return "home";
	}
	
	@RequestMapping(value = "/signUp", method = POST)
	public String signUp(){
		return "home";
	}
	
}
