package com.starkinc.wtopic.contoller;

import static org.springframework.web.bind.annotation.RequestMethod.PUT;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.starkinc.wtopic.constants.Constants;
import com.starkinc.wtopic.entity.Message;
import com.starkinc.wtopic.entity.Topic;
import com.starkinc.wtopic.exception.TopicException;
import com.starkinc.wtopic.service.TopicService;
import com.starkinc.wtopic.util.TopicWebUtils;

@Controller
public class TopicMessageContoller {
	
	private TopicService topicService;
	
	@RequestMapping(value = "/topic", method = PUT)
	public String firstPost(@RequestParam String topicName, @RequestParam String firstMessage, RedirectAttributes redirectAttributes){
		Topic topic = topicService.updateTopic(topicName, firstMessage);
		List<Message> messages = topic.getMessages();
		redirectAttributes.addFlashAttribute(Constants.MESSAGES,messages);
		return TopicWebUtils.appendPath("redirect:/topic", topicName);
	}

	@Autowired
	public void setTopicService(TopicService topicService) {
		this.topicService = topicService;
	}
	
	@ExceptionHandler(TopicException.class)
	public String handleTopicException(RedirectAttributes redirectAttrs, TopicException ex){
		redirectAttrs.addFlashAttribute(Constants.TOPIC_ERROR, ex.getMessage());
		return "redirect:/home";
	}

}
