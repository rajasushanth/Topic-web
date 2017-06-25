package com.starkinc.wtopic.exception;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ClientResponseException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1923525360082073701L;
	private String message;
	private HttpStatus httpStatus;
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public HttpStatus getHttpStatus() {
		return httpStatus;
	}
	public void setHttpStatus(HttpStatus httpStatus) {
		this.httpStatus = httpStatus;
	}
	public ClientResponseException(String message, HttpStatus httpStatus) {
		super();
		this.message = message;
		this.httpStatus = httpStatus;
	}
	public ClientResponseException() {
		super();
	}
	public ClientResponseException(String message) {
		super();
		this.message = message;
	}
	
	
	

}
