package com.lovemesomecoding.event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEvent;

import com.lovemesomecoding.user.User;
import com.lovemesomecoding.utils.ObjectUtils;

public class UserCreateEvent extends ApplicationEvent {
	
	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	public UserCreateEvent(User user) {
		super(user);
		log.info("UserCreateEvent, user={}", ObjectUtils.toJson(user));
	}

}
