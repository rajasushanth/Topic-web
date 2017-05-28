package com.starkinc.wtopic.constants;

public final class Constants {
	
	public static final String BASE_URL = "${rest.client.url.base}";
	public static final String LOGIN = "${rest.client.resource.user.login}";
	public static final String USER_RESOURCE = "${rest.client.resource.users}";
	public static final String TOPIC_RESOURCE = "${rest.client.resource.topics}";
	public static final String USERNAME = "username";
	public static final String CONTENT_TYPE = "Content-Type";
	public static final String SUCCESS = "success";
	public static final String ERROR = "error";
	
	public static final String SALT = "${salt}";
	public static final String SECRECT_KEY = "${secret.key}";
	public static final String TILT = "~";
	public static final String PASSWORD_PLACEHOLDER = "SIUAKJ5365";
	public static final String ROLE_USER = "ROLE_USER";
	public static final String HEADER_STRING = "${header.string}";
	
	public static final String USER_SESSION = "USER_SESSION";
	public static final String SLASH = "/";
	public static final String MESSAGES = "messages";
	public static final String IS_CREATED = "isCreated";
	public static final String TOPIC_NAME = "topicName";
	public static final String TOPIC_ERROR = "topicError";
	public static final String NO_RECORD_FOUND = "No record found";
	public static final String TOPIC_NOT_FOUND = "Topic * not found";
	public static final String BY_AUTHOR = "${rest.client.resource.topics.byAuthor}"; 
	public static final String SYMBOL_QUESTION = "?";
	public static final String SYMBOL_AMPERSAND = "&";
	public static final String SYMBOL_EQUAL_TO = "=";
	public static final String AUTHOR = "author";
	public static final String SKIP = "skip";
	private Constants(){
	}
}
