package com.starkinc.wtopic.contoller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.starkinc.wtopic.entity.Topic;

import static org.springframework.web.bind.annotation.RequestMethod.POST;;;

@Controller
public class HomeController {
	
	@RequestMapping(value="/topic", method = POST)
	public String createTopic(Topic topic){
		return "";
	}

}
