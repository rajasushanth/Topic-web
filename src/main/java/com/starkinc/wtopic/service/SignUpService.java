package com.starkinc.wtopic.service;

import java.util.Map;

import com.starkinc.wtopic.entity.TopicUser;

public interface SignUpService {
	
	public Map<String, String> signUp(TopicUser user);

}
