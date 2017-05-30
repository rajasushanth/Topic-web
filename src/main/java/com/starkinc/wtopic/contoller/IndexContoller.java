package com.starkinc.wtopic.contoller;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.starkinc.wtopic.constants.Constants;
import com.starkinc.wtopic.entity.TopicUser;
import com.starkinc.wtopic.exception.SignUpException;
import com.starkinc.wtopic.service.TopicService;
import com.starkinc.wtopic.serviceImpl.SignUpServiceImpl;;

@Controller
public class IndexContoller {
	
	private SignUpServiceImpl signUpServiceImpl;
	private TopicService topicService;
		
	@RequestMapping("/")
	public String index(){
		return "index";
	}
	
	@RequestMapping("/home")
	public String home(Model model, @RequestParam(value = "page", required = false) Integer page){
		List<String> topicNameList = topicService.getTopicsByAuthor(page==null?0:page);
		model.addAttribute(Constants.TOPIC_NAME_LIST, topicNameList);
		return "home";
	}
	
	@RequestMapping(value = "/signUp", method = POST)
	public String signUp(TopicUser user, HttpServletRequest request, RedirectAttributes redirectAttrs){
		signUpServiceImpl.signUp(user);
		return "redirect:/home";
	}
	
	@Autowired
	public void setSignUpServiceImpl(SignUpServiceImpl signUpServiceImpl) {
		this.signUpServiceImpl = signUpServiceImpl;
	}
	
	@Autowired
	public void setTopicService(TopicService topicService) {
		this.topicService = topicService;
	}

	@ExceptionHandler(SignUpException.class)
	public String handleSignUpException(RedirectAttributes redirectAttrs, SignUpException ex){
		redirectAttrs.addFlashAttribute("signUpError", ex.getMessage());
		return "redirect:/";
	}
	
	@ExceptionHandler(Throwable.class)
	public String handleIndexControllerException(RedirectAttributes redirectAttrs, Throwable th){
		redirectAttrs.addFlashAttribute("generalError", th.getMessage());
		return "redirect:/";
	}
	
}
