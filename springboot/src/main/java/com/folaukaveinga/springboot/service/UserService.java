package com.folaukaveinga.springboot.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.folaukaveinga.springboot.domain.Address;
import com.folaukaveinga.springboot.domain.User;
import com.folaukaveinga.springboot.utility.RandomUtil;

@Service
public class UserService {
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	public User save(User user){
		user.setId(RandomUtil.getRandomNumber());
		log.info("user saved");
		log.info(user.toString());
		return user;
	}
	
	public User get(int id){
		log.info("get user by id: {}",id);
		return new User(id,RandomUtil.getRandomString(),RandomUtil.getRandomNumber(), new Address());
	}
}
