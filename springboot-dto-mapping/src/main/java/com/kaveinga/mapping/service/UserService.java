package com.kaveinga.mapping.service;

import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.kaveinga.mapping.model.User;
import com.kaveinga.mapping.utility.RandomGeneratorUtils;

@Service
public class UserService {
	
	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	public User create(User user) {
		return user;
	}

	public User getById(Long id) {
		User user = new User();
		user.setDateOfBirth(new Date());
		user.setEmail(RandomGeneratorUtils.getAlphaNumeric(10)+"@gmail.com");
		user.setFirstName(RandomGeneratorUtils.getAlphaNumeric(10));
		user.setLastName(RandomGeneratorUtils.getAlphaNumeric(10));
		return user;
	}

}
