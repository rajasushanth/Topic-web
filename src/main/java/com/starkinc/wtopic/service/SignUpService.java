package com.starkinc.wtopic.service;

import org.springframework.stereotype.Service;

import com.starkinc.wtopic.entity.TopicUser;

@Service
public interface SignUpService {
	
	public String signUp(TopicUser user);

}
