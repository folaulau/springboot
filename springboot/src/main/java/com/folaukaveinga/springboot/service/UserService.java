package com.folaukaveinga.springboot.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.folaukaveinga.springboot.domain.User;
import com.folaukaveinga.springboot.repository.UserRepository;

@Service
public class UserService {
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private UserRepository userRepository;
	
	public User save(User user){
		user = userRepository.save(user);
		log.info("user saved");
		log.info(user.toString());
		return user;
	}
	
	public User get(long id){
		log.info("get user by id: {}",id);
		return userRepository.findOne(id);
	}
	
	public List<User> getAll(){
		return userRepository.findAll();
	}

	public User update(User user) {
		return userRepository.save(user);
	}
}
