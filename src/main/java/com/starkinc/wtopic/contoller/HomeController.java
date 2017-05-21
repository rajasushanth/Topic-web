package com.starkinc.wtopic.contoller;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.starkinc.wtopic.entity.Topic;
import com.starkinc.wtopic.exception.TopicException;
import com.starkinc.wtopic.service.TopicService;;;

@Controller
public class HomeController {
	
	
	private TopicService topicService; 
	
	@RequestMapping(value="/topic", method = POST)
	public String createTopic(Topic topic, RedirectAttributes redirectAttrs){
		Topic topicResponse = topicService.createTopic(topic);
		redirectAttrs.addFlashAttribute("topicName",topicResponse.getTopicName());
		return "redirect:/topic";
	}
	
	@RequestMapping("/topic")
	public String topic(){
		return "topic";
	}
	
	
	@Autowired
	public void setTopicService(TopicService topicService) {
		this.topicService = topicService;
	}
	
	@ExceptionHandler(TopicException.class)
	public String handleTopicException(RedirectAttributes redirectAttrs, TopicException ex){
		redirectAttrs.addAttribute("topicError", ex.getMessage());
		return "";
	}

}
