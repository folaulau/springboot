package com.kaveinga.jms;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import com.kaveinga.user.User;

@Component
public class UserConsumer {
	
	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	@JmsListener(destination = "user", subscription="user_sync", containerFactory="jmsListenerContainerFactory")
    public void receiveMessage(String json) {
		log.info("receiveMessage(..)");
        User user = User.fromJson(json);
        log.info("Received USER: {}",user.toJson());
    }

}
