package com.starkinc.wtopic.aspects;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.FOUND;
import static org.springframework.http.HttpStatus.GATEWAY_TIMEOUT;
import static org.springframework.http.HttpStatus.OK;

import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.starkinc.wtopic.constants.Constants;
import com.starkinc.wtopic.dto.UserSession;
import com.starkinc.wtopic.exception.ClientResponseException;
import com.starkinc.wtopic.exception.SessionExpiredException;
import com.starkinc.wtopic.util.TopicWebUtils;

/**
 * @author RajaSushanth
 *
 */
@Aspect
@Component
public class TopicClientAspect {
	
	private Logger LOG = LoggerFactory.getLogger(TopicClientAspect.class);
	
	@Pointcut("execution(* com.starkinc.wtopic.restClient.TopicClient.*(..))")
	public void includeMethod(){}
	
	@AfterReturning(value = "includeMethod()", returning = "responseEntity")
	public void updateTokens(JoinPoint joinPoint, ResponseEntity<? extends Object> responseEntity){
		if(null != responseEntity){
			if(StringUtils.containsAny(responseEntity.getStatusCode().toString(), OK.toString(), FOUND.toString(), CREATED.toString())){
				String token = responseEntity.getHeaders().getFirst(Constants.REFRESHED_TOKEN);
				if(StringUtils.isNotBlank(token)){
					LOG.info("***** ***** ***** Refreshing tokens ***** ***** *****");
					UserSession currentUserSession = TopicWebUtils.getCurrentUserSession();
					currentUserSession.setToken(token);
				}
			}
		}
	}
	
	@AfterThrowing(value = "includeMethod()", throwing = "exception")
	public void handleTokenExpiry(Exception exception){
		ClientResponseException ex = (ClientResponseException)exception;
		if(null != exception && GATEWAY_TIMEOUT == ex.getHttpStatus()){
			throw new SessionExpiredException(ex);
		}
	}
	
}
