package com.starkinc.wtopic.interceptor;

import java.io.IOException;

import org.springframework.http.HttpRequest;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.support.HttpRequestWrapper;

import com.starkinc.wtopic.constants.Constants;

/**
 * @author RajaSushanth
 *
 */
public class HeaderRequestInterceptor implements ClientHttpRequestInterceptor {
	
	/*
	 * Added to handle org.springframework.web.client.HttpClientErrorException: 415 null
	 */
	@Override
	public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution)
			throws IOException {
		HttpRequest requestWrapper = new HttpRequestWrapper(request);
		requestWrapper.getHeaders().set(Constants.CONTENT_TYPE, MediaType.APPLICATION_JSON.toString());
		return execution.execute(requestWrapper, body);
	}

}
