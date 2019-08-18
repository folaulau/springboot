package com.lovemesomecoding.user;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amazonaws.services.sqs.model.MessageAttributeValue;
import com.lovemesomecoding.utils.ObjectUtils;

@Service
public class UserServiceImp implements UserService {

	private Logger log = LoggerFactory.getLogger(this.getClass()); 

	@Override
	public User create(User user) {
		log.debug("create user={}",ObjectUtils.toJson(user));
		
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
