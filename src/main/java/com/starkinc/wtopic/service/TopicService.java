package com.starkinc.wtopic.service;

import com.starkinc.wtopic.entity.Topic;
import com.starkinc.wtopic.entity.UserSession;

public interface TopicService {
	
	public Topic createTopic(Topic topic, UserSession userSession);
	public Topic updateTopic(Topic topic);

}
