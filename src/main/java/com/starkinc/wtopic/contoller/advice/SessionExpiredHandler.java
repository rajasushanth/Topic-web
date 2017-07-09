package com.starkinc.wtopic.contoller.advice;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.starkinc.wtopic.exception.SessionExpiredException;

/**
 * @author RajaSushanth
 *
 */
@ControllerAdvice
public class SessionExpiredHandler {
	
	@ExceptionHandler(SessionExpiredException.class)
	public String handleSessionExpiry(HttpServletRequest request, SessionExpiredException ex){
		request.getSession().setAttribute("failureMessage", ex.getMessage());
		return "redirect:/";
	}

}
