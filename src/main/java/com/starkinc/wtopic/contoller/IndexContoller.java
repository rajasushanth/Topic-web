package com.starkinc.wtopic.contoller;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.starkinc.wtopic.constants.Constants;
import com.starkinc.wtopic.dto.ResetPasswordDTO;
import com.starkinc.wtopic.entity.TopicUser;
import com.starkinc.wtopic.exception.SignUpException;
import com.starkinc.wtopic.service.RecoverAccountService;
import com.starkinc.wtopic.serviceImpl.SignUpServiceImpl;;

@Controller
public class IndexContoller {
	
	private SignUpServiceImpl signUpServiceImpl;
	private RecoverAccountService recoverAccountService;
		
	@RequestMapping("/")
	public String index(){
		return "index";
	}
	
	@RequestMapping("/recover")
	public String getSecurityQuestion(@RequestParam(required = false) String username, Model model){
		if(StringUtils.isNotBlank(username)){
			String question = recoverAccountService.getSecurityQuestion(username);
			model.addAttribute("question", question);
			model.addAttribute("username", username);
		}
		return "recover";
	}
	
	@RequestMapping(value = "/recover/{username}", method = RequestMethod.POST)
	public String recoverAccount(@PathVariable("username") String username,
			ResetPasswordDTO resetPasswordDTO, RedirectAttributes redirectAttributes){
		if(StringUtils.isNoneBlank(username, resetPasswordDTO.getAnswer(), resetPasswordDTO.getPassword()) && resetPasswordDTO.getPassword().length() > 2){
			boolean result = recoverAccountService.recoverAccount(username, resetPasswordDTO);
			redirectAttributes.addFlashAttribute(Constants.UPDATE_RESULT, result?Constants.SUCESSFULL_UPDATE:Constants.UNSUCESSFULL_UPDATE);
		}else{
			redirectAttributes.addFlashAttribute(Constants.UPDATE_RESULT,Constants.EMPTY_PASSWORD);
		}
		return "redirect:/recover?username="+username;
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
	public void setRecoverAccountService(RecoverAccountService recoverAccountService) {
		this.recoverAccountService = recoverAccountService;
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
