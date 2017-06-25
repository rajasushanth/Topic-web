package com.starkinc.wtopic.exception;

public class SessionExpiredException extends ClientResponseException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 306182337072485815L;
	
	public SessionExpiredException() {
		super();
	}

	public SessionExpiredException(ClientResponseException e) {
		super(e.getMessage(), e.getHttpStatus());
	}

}
