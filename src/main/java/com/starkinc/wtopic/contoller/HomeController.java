package com.starkinc.wtopic.contoller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.starkinc.wtopic.constants.Constants;
import com.starkinc.wtopic.entity.Topic;
import com.starkinc.wtopic.entity.UserSession;
import com.starkinc.wtopic.exception.TopicException;
import com.starkinc.wtopic.service.TopicService;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;;;

@Controller
public class HomeController {
	
	
	private TopicService topicService; 
	
	@RequestMapping(value="/topic", method = POST)
	public String createTopic(Topic topic, HttpServletRequest request){
		UserSession userSession = (UserSession) request.getSession().getAttribute(Constants.USER_SESSION);
		topicService.createTopic(topic, userSession);
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
