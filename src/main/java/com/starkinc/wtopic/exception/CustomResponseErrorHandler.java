package com.starkinc.wtopic.exception;

import java.io.IOException;

import org.apache.commons.io.IOUtils;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.ResponseErrorHandler;

import com.fasterxml.jackson.databind.ObjectMapper;

public class CustomResponseErrorHandler implements ResponseErrorHandler {
	
	private ResponseErrorHandler responseErrorHandler = new DefaultResponseErrorHandler();
	private ObjectMapper objectMapper = new ObjectMapper();
 
	@Override
	public boolean hasError(ClientHttpResponse response) throws IOException {
		return responseErrorHandler.hasError(response);
	}

	@Override
	public void handleError(ClientHttpResponse response) throws IOException {
		String resposeBody = IOUtils.toString(response.getBody(), "UTF-8");
		TopicException topicException = objectMapper.readValue(resposeBody, TopicException.class);
		topicException.setHttpStatus(response.getStatusCode());
		throw topicException;
	}

}
