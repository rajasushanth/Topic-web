package com.starkinc.wtopic.util;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.starkinc.wtopic.constants.Constants;
import com.starkinc.wtopic.dto.UserSession;

public abstract class TopicWebUtils {

	public static HttpServletRequest getRequest() {
		return ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
	}
	
	public static HttpEntity<Object> buildEntityWithToken(Object topic, String token){
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
		headers.add("Authorization", token);
		return new HttpEntity<Object>(topic, headers);
	}
	
	public static UserSession getCurrentUserSession(){
		HttpServletRequest request = getRequest();
		return (UserSession) request.getSession().getAttribute(Constants.USER_SESSION);
	}
	
	public static void bindUserSession(String username, String token){
		HttpServletRequest request = getRequest();
		UserSession userSession = new UserSession(token, username, null);
		request.getSession().setAttribute(Constants.USER_SESSION, userSession);
	}
	
	public static String appendPath(String base, String path){
		return base + Constants.SLASH + path;
	}
	
	public static String appendQuery(String base, String author, int skip){
		return base + Constants.SYMBOL_QUESTION + 
				Constants.AUTHOR + Constants.SYMBOL_EQUAL_TO + author +
				Constants.SYMBOL_AMPERSAND +
				Constants.SKIP + Constants.SYMBOL_EQUAL_TO + skip;
	}
}
