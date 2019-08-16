package com.lovemesomecoding.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lovemesomecoding.aws.sqs.SQSMessage;
import com.lovemesomecoding.aws.sqs.SQSMessageAction;
import com.lovemesomecoding.aws.sqs.SQSMessageType;
import com.lovemesomecoding.aws.sqs.SQSQueue;
import com.lovemesomecoding.aws.sqs.SQSService;
import com.lovemesomecoding.utils.ObjectUtils;

@Service
public class UserServiceImp implements UserService {

	private Logger log = LoggerFactory.getLogger(this.getClass()); 
	
	@Autowired
	private SQSService sqsService;

	@Override
	public User create(User user) {
		log.debug("create user={}",ObjectUtils.toJson(user));
		sqsService.sendMessage(SQSQueue.ACCOUNTS_QUEUE_URL, new SQSMessage(SQSMessageType.EVENT, SQSMessageAction.SEND_USER_WELCOME_EMAIL, ObjectUtils.toJson(user)));
		
		return user;
	}

	@Override
	public User getById(Long id) {
		// TODO Auto-generated method stub

		return null;
	}

	@Override
	public void sendWelcomeEmail(User user) {
		log.debug("welcome email sent to {}", user.getEmail());
		
		//throw new RuntimeException("fake dead!");
	}

	
}
