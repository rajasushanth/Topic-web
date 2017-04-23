package com.starkinc.wtopic.security;

import java.util.Collections;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

public class AuthenticationService {

	public static Authentication getAuthentication(HttpServletRequest req) {
		String temp = (String)req.getSession().getAttribute("temp");
		if("pass".equals(temp)){
			return new UsernamePasswordAuthenticationToken(req.getParameterMap(), null, Collections.emptyList());
		}
		return null;
	}

}
