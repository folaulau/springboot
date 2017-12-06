package com.folaukaveinga.springboot.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.folaukaveinga.springboot.domain.User;

@Service
public class UserService {
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	public User save(User user){
		log.info("save user");
		log.info(user.toString());
		user.setId(1);
		log.info("user saved");
		log.info(user.toString());
		return user;
	}
	
	public User get(int id){
		log.info("get user by id: {}",id);
		return new User(id,"test name",9);
	}
}
