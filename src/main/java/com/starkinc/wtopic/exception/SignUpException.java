package com.starkinc.wtopic.exception;

import org.springframework.http.HttpStatus;

public class SignUpException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3826781453318629546L;
	
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
	public SignUpException(String message, HttpStatus httpStatus) {
		super();
		this.message = message;
		this.httpStatus = httpStatus;
	}
	

}
