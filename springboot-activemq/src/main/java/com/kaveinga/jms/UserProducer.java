package com.kaveinga.jms;

import org.apache.activemq.command.ActiveMQTopic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import com.kaveinga.user.User;

@Component
public class UserProducer {
	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private JmsTemplate jmsTemplate;
	
	public void send(User user) {
		log.info("send(..), USER: {}", user.toJson());
		try {
			//jmsTemplate.convertAndSend("user_sync", user.toJson());
			log.info("user sent to queue");
			jmsTemplate.convertAndSend("user", user.toJson());
		} catch (Exception e) {
			log.error("Error, msg: "+e.getMessage());
		}
	}
	
	public void sendToTopic(User user) {
		log.info("send(..), USER: {}", user.toJson());
		try {
			log.info("user sent to topic");
			user.setName("topic name");
			jmsTemplate.convertAndSend("user_sync", user.toJson());
		} catch (Exception e) {
			log.error("Error, msg: "+e.getMessage());
		}
	}

}
