package com.starkinc.wtopic.contoller;

import static org.springframework.web.bind.annotation.RequestMethod.PUT;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.starkinc.wtopic.service.TopicService;

@Controller
public class WebTopicContoller {
	
	private TopicService topicService;
	
	@RequestMapping(value = "/topic", method = PUT)
	public String firstPost(@RequestParam String topicName, @RequestParam String firstMessage){
		topicService.updateTopic(topicName, firstMessage);
		return "redirect:/topic";
	}

	@Autowired
	public void setTopicService(TopicService topicService) {
		this.topicService = topicService;
	}
	
	

}
