package com.starkinc.wtopic.exception;

/**
 * @author RajaSushanth
 *
 */
public class RecoverException extends ClientResponseException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7735788158688867682L;

	public RecoverException() {
		super();
	}

	public RecoverException(ClientResponseException e) {
		super(e.getMessage(), e.getHttpStatus());
	}
	
	
	

}
