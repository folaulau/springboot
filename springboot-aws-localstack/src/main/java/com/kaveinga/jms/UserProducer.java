package com.kaveinga.jms;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.model.PublishRequest;
import com.amazonaws.services.sns.model.PublishResult;
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
			jmsTemplate.convertAndSend("local-doctor-queue", user.toJson());
		} catch (Exception e) {
			log.error("Error, msg: "+e.getMessage());
		}
	}
	
	@Autowired
	private AmazonSNS amazonSns;
	
	private String accountSyncTopicArn = "arn:aws:sns:us-east-1:123456789012:local-account-sync-topic";
	
	// =================================== Account =====================================
	public void syncUser(User user) {
		log.info("syncUser(..)");
		try {
			PublishRequest publishRequest = new PublishRequest();
			publishRequest.setTopicArn(accountSyncTopicArn);
			publishRequest.setMessage(user.toJson());
			PublishResult publishResult = amazonSns.publish(publishRequest);
			log.debug("publish message id: {}",publishResult.getMessageId());
		} catch (Exception e) {
			log.warn("Exception, msg: {}",e.getMessage());
		}
	}

}
