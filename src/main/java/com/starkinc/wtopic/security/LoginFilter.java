package com.starkinc.wtopic.security;

import java.io.IOException;
import java.util.Collections;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.starkinc.wtopic.constants.Constants;

public class LoginFilter extends AbstractAuthenticationProcessingFilter {
	
	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

	protected LoginFilter(String defaultFilterProcessesUrl, AuthenticationManager authenticationManager) {
		super(new AntPathRequestMatcher(defaultFilterProcessesUrl));
		setAuthenticationManager(authenticationManager);
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res)
			throws AuthenticationException, IOException, ServletException {
		return getAuthenticationManager().authenticate(
				new UsernamePasswordAuthenticationToken(concatUserPassword(req.getParameter("username"), req.getParameter("password")),
						Constants.PASSWORD_PLACEHOLDER, Collections.emptyList()));
	}
	

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		redirectStrategy.sendRedirect(request, response, "home");
	}
	
	

	@Override
	protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException failed) throws IOException, ServletException {
		request.getSession().setAttribute("failureMessage", failed.getMessage());
		redirectStrategy.sendRedirect(request, response, "");
	}

	private String concatUserPassword(String userName, String password) {
		return userName + Constants.TILT + password;

	}

}
