package com.starkinc.wtopic.constants;

public final class Constants {
	
	public static final String BASE_URL = "${rest.client.url.base}";
	public static final String USER_RESOURCE = "${rest.client.resource.user}";
	public static final String USERNAME = "username";
	public static final String CONTENT_TYPE = "Content-Type";
	public static final String SUCCESS = "success";
	public static final String ERROR = "error";
	
	public static final String SALT = "${salt}";
	public static final String SECRECT_KEY = "${secret.key}";
	
	private Constants(){
	}
}
