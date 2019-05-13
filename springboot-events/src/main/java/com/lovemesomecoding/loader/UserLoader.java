package com.lovemesomecoding.loader;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lovemesomecoding.event.publisher.UserEventPublisher;
import com.lovemesomecoding.user.User;

@Component
public class UserLoader {

	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private UserEventPublisher userEventPublisher;
	
	@PostConstruct
	public void init() {
		User user = new User();
		user.setEmail("folaukaveinga@gmail.com");
		user.setFirstName("Folau");
		user.setLastName("Kaveinga");
		
		userEventPublisher.processUserCreate(user);
	}
}
