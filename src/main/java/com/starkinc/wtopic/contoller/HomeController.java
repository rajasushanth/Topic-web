package com.starkinc.wtopic.contoller;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.starkinc.wtopic.constants.Constants;
import com.starkinc.wtopic.entity.Message;
import com.starkinc.wtopic.entity.Topic;
import com.starkinc.wtopic.exception.TopicException;
import com.starkinc.wtopic.service.TopicService;
import com.starkinc.wtopic.util.TopicWebUtils;;;

@Controller
public class HomeController {
	
	private TopicService topicService; 
	
	@RequestMapping(value="/topic", method = POST)
	public String createTopic(Topic topic, RedirectAttributes redirectAttrs){
		Topic topicResponse = topicService.createTopic(topic);
		String topicName = topicResponse.getTopicName();
		redirectAttrs.addFlashAttribute(Constants.TOPIC_NAME, topicName);
		redirectAttrs.addFlashAttribute(Constants.IS_CREATED,true);
		return TopicWebUtils.appendPath("redirect:/topic", topicName);
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping("/topic/{topicName}")
	public String getTopic(HttpServletRequest request, RedirectAttributes redirectAttributes, @PathVariable(Constants.TOPIC_NAME) String topicNameFromPath){
		Map<String, ?> flashMap = RequestContextUtils.getInputFlashMap(request);
		List<Message> messages = null;
		if(null != flashMap){
			Boolean isCreated = (Boolean)flashMap.get(Constants.IS_CREATED);
			if(null ==isCreated){
				messages =  (List<Message>) flashMap.get(Constants.MESSAGES);
				if(null == messages || messages.isEmpty()){
					String topicName = (String)flashMap.get(Constants.TOPIC_NAME);
					if(StringUtils.isNotBlank(topicName)){
						messages = getTopicByName(topicName);
					}else{
						return "error";
					}
					redirectAttributes.addFlashAttribute(Constants.MESSAGES, messages);
				}
			}

		}else {
			messages = getTopicByName(topicNameFromPath);
			request.setAttribute(Constants.MESSAGES, messages);
		}
		return "topic";
	}

	private List<Message> getTopicByName(String topicName) {
		Topic topic;
		List<Message> messages;
		topic = topicService.getTopicByName(topicName);
		messages = topic.getMessages();
		return messages;
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
