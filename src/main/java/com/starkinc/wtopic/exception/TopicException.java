package com.starkinc.wtopic.exception;

public class TopicException extends ClientResponseException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5313428338015008063L;

	public TopicException() {
		super();
	}
	
	public TopicException(ClientResponseException e) {
		super(e.getMessage(), e.getHttpStatus());
	}
	
	public TopicException(String message){
		super(message);
	}

}
