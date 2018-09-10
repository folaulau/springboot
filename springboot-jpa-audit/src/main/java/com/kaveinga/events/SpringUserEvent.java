package com.kaveinga.events;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class SpringUserEvent implements ApplicationListener<UserEvent>{
	
	private Logger log = LoggerFactory.getLogger(this.getClass());

	@Override
	public void onApplicationEvent(UserEvent event) {
		log.info("Received spring custom event - " + event.getUser().toJson());
	}

}
