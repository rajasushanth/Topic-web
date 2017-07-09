package com.starkinc.wtopic.contoller;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.starkinc.wtopic.constants.Constants;
import com.starkinc.wtopic.dto.SearchDTO;
import com.starkinc.wtopic.dto.TopicsDTO;
import com.starkinc.wtopic.entity.Message;
import com.starkinc.wtopic.entity.Topic;
import com.starkinc.wtopic.exception.TopicException;
import com.starkinc.wtopic.service.TopicService;
import com.starkinc.wtopic.util.TopicWebUtils;;;

/**
 * @author RajaSushanth
 *
 */
@Controller
public class HomeController {
	
	private TopicService topicService;
	
	
	@RequestMapping("/home")
	public String home(Model model, @RequestParam(required = false) String page){
		Integer pageGo = null;
		try{
			pageGo = (null != page)?Integer.valueOf(page):null;
		}catch (NumberFormatException e) {}
		TopicsDTO topicsDTO = topicService.getTopicsByAuthor((pageGo==null || pageGo < 1)?1:pageGo);
		model.addAttribute(Constants.TOPIC_DTO, topicsDTO);
		return "home";
	}
	
	@RequestMapping(value="/topic", method = POST)
	public String createTopic(Topic topic, RedirectAttributes redirectAttrs){
		if(null != topic && StringUtils.isNotBlank(topic.getTopicName()) && topic.getTopicName().length() > 2){
			Topic topicResponse = topicService.createTopic(topic);
			String topicName = topicResponse.getTopicName();
			redirectAttrs.addFlashAttribute(Constants.TOPIC_NAME, topicName);
			redirectAttrs.addFlashAttribute(Constants.IS_CREATED,true);
			return TopicWebUtils.appendPath("redirect:/topic", topicName);
		}else{
			throw new TopicException(Constants.TOPIC_CREATION_ERROR);
		}
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
						throw new TopicException(Constants.TOPIC_REDIRECTION_ERROR);
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
	
	@RequestMapping(value = "/topic/search")
	public String searchTopic(RedirectAttributes redirectAttributes, @RequestParam(required = false) String author,
			@RequestParam(required = false) String topicName, @RequestParam(required = false) String page){
		Integer pageGo = null;
		try{
			pageGo = (null != page)?Integer.valueOf(page):null;
		}catch (NumberFormatException e) {}
		SearchDTO searchDTORequest = new SearchDTO(author, topicName);
		SearchDTO searchDTO = topicService.getTopicsByAuthorAndTopicName(searchDTORequest, pageGo==null?1:pageGo);
		redirectAttributes.addFlashAttribute(Constants.SEARCH_DTO, searchDTO);
		return "redirect:/home";
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
