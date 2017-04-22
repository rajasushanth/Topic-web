package com.starkinc.wtopic.contoller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import com.starkinc.wtopic.entity.TopicUser;
import com.starkinc.wtopic.service.LoginService;

@Controller
@RequestMapping(produces = APPLICATION_JSON_VALUE)
public class IndexContoller {
	
	private LoginService loginService;
	
	@RequestMapping("/")
	public String index(){
		return "index";
	}
	
	@RequestMapping("/home")
	public String home(){
		return "home";
	}
	
	@RequestMapping("/logoutnow")
	public String logout(){
		return "redirect:/";
	}
	
	@RequestMapping(value = "/login", method = POST)
	public String login(Model model, @ModelAttribute TopicUser user){
		boolean isAuthenticated = false;
		String landingPage = "index";
		if(null != user && 
				StringUtils.isNotBlank(user.getUsername()) && StringUtils.isNotBlank(user.getPassword())){
			isAuthenticated = loginService.validateLogin(user.getUsername(), user.getPassword());
			if(isAuthenticated){
				landingPage = "redirect:home";
			}else{
				model.addAttribute("error", "Invalid credentials");
			}
		}
		return landingPage;
	}
	
	@Autowired
	public IndexContoller(LoginService loginService) {
		this.loginService = loginService;
	}
	
	
	
}
