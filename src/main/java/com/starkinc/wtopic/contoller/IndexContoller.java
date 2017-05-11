package com.starkinc.wtopic.contoller;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.starkinc.wtopic.entity.TopicUser;
import com.starkinc.wtopic.serviceImpl.SignUpServiceImpl;
import com.starkinc.wtopic.util.WebUtil;;

@Controller
@RequestMapping(produces = APPLICATION_JSON_VALUE)
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
	public String signUp(TopicUser user, HttpServletRequest request, final RedirectAttributes redirectAttrs){
		Map<String, String> responseMap = signUpServiceImpl.signUp(user);
		return WebUtil.getViewAsString(responseMap, request, redirectAttrs);
	}
	
	@Autowired
	public void setSignUpServiceImpl(SignUpServiceImpl signUpServiceImpl) {
		this.signUpServiceImpl = signUpServiceImpl;
	}
	
	
}
