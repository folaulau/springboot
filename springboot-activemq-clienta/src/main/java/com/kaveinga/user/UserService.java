package com.kaveinga.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kaveinga.jms.UserProducer;

@Service
public class UserService {
	private Logger         log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private UserProducer userProducer;
	
	public void save(User user) {
		userProducer.send(user);
	}
}
