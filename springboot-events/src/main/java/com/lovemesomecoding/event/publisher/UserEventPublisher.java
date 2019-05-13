package com.lovemesomecoding.event.publisher;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import com.lovemesomecoding.event.UserCreateEvent;
import com.lovemesomecoding.event.UserUpdateEvent;
import com.lovemesomecoding.user.User;
import com.lovemesomecoding.utils.ObjectUtils;

@Component
public class UserEventPublisher {
	
	private Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
    private ApplicationEventPublisher applicationEventPublisher;
	
	public void processUserCreate(User user) {
		
		UserCreateEvent userEvent = new UserCreateEvent(user);
		log.info("processUserCreate, userEvent={}",ObjectUtils.toJson(userEvent));
		
		applicationEventPublisher.publishEvent(userEvent);
	}
	
	public void processUserUpdate(User user) {
		
		UserUpdateEvent userEvent = new UserUpdateEvent(user);
		log.info("processUserUpdate, userEvent={}",ObjectUtils.toJson(userEvent));
		
		applicationEventPublisher.publishEvent(userEvent);
	}
}
