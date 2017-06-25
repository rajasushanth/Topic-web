package com.starkinc.wtopic.exception;

public class SignUpException extends ClientResponseException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3826781453318629546L;

	public SignUpException() {
		super();
	}

	public SignUpException(ClientResponseException e) {
		super(e.getMessage(), e.getHttpStatus());
	}
	
	
	

}
