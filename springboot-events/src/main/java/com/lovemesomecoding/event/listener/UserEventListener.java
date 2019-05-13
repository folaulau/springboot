package com.lovemesomecoding.event.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.lovemesomecoding.event.UserCreateEvent;
import com.lovemesomecoding.event.UserUpdateEvent;
import com.lovemesomecoding.user.User;
import com.lovemesomecoding.utils.ObjectUtils;
/*
// synchronous call
@Component
public class UserEventListener implements  ApplicationListener<UserCreateEvent>{
	
	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Override
	public void onApplicationEvent(UserCreateEvent event) {
		log.info("onApplicationEvent, event={}",ObjectUtils.toJson(event));
		User user = (User) event.getSource();
		log.info("onApplicationEvent, user={}",ObjectUtils.toJson(user));
	}
}
*/

// asynchronous call
@Component
public class UserEventListener{
	
	private Logger log = LoggerFactory.getLogger(this.getClass());

	
	@Async
    @EventListener
    public void handleUserCreateEvent(UserCreateEvent createEvent) {
		log.info("handleUserCreateEvent, event={}",ObjectUtils.toJson(createEvent));
    }
	
	// filter
	@Async
    @EventListener(condition = "#event.source.email=='folaukaveinga@gmail.com'")
    public void handleUserUpdateEvent(UserUpdateEvent event) {
		log.info("handleUserUpdateEvent, event={}",ObjectUtils.toJson(event));
    }
}
