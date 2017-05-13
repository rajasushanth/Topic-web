package com.starkinc.wtopic.security;

import java.util.Collections;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

public class AuthenticationService {

	public static Authentication getAuthentication(HttpServletRequest req) {
		String token = (String)req.getSession().getAttribute("token");
		if(StringUtils.isNotBlank(token)){
			return new UsernamePasswordAuthenticationToken(req.getParameterMap(), null, Collections.emptyList());
		}
		return null;
	}

}
