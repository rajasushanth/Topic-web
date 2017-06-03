package com.starkinc.wtopic.security;

import java.util.Collections;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import com.starkinc.wtopic.constants.Constants;
import com.starkinc.wtopic.dto.UserSession;

public class AuthenticationService {

	public static Authentication getAuthentication(HttpServletRequest req) {
		UserSession userSession = (UserSession)req.getSession().getAttribute(Constants.USER_SESSION);
		if(null != userSession && StringUtils.isNotBlank(userSession.getToken()) && StringUtils.isNoneBlank(userSession.getUsername())){
			return new UsernamePasswordAuthenticationToken(req.getParameterMap(), null, Collections.emptyList());
		}
		return null;
	}

}
