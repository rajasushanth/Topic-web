package com.starkinc.wtopic.contoller;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.starkinc.wtopic.constants.Constants;
import com.starkinc.wtopic.entity.TopicUser;
import com.starkinc.wtopic.entity.UserSession;
import com.starkinc.wtopic.exception.SignUpException;
import com.starkinc.wtopic.serviceImpl.SignUpServiceImpl;;

@Controller
public class IndexContoller {
	
	private SignUpServiceImpl signUpServiceImpl;
		
	@RequestMapping("/")
	public String index(){
		return "index";
	}
	
	@RequestMapping("/home")
	public String home(){
		return "home";
	}
	
	@RequestMapping(value = "/signUp", method = POST)
	public String signUp(TopicUser user, HttpServletRequest request, RedirectAttributes redirectAttrs){
		String token = signUpServiceImpl.signUp(user);
		UserSession userSession = new UserSession(token, user.getUsername(), null);
		request.getSession().setAttribute(Constants.USER_SESSION, userSession);
		return "home";
	}
	
	@Autowired
	public void setSignUpServiceImpl(SignUpServiceImpl signUpServiceImpl) {
		this.signUpServiceImpl = signUpServiceImpl;
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
