package com.folaukaveinga.testing.service;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.assertj.core.util.Arrays;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.folaukaveinga.testing.domain.User;
import com.folaukaveinga.testing.repository.UserRepository;

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
		return userRepository.getOne(id);
	}
	
	public List<User> getByAge(int age){
		log.info("get user by age: {}",age);
		try {
			return userRepository.findByAge(age).get();
		} catch (InterruptedException | ExecutionException e) {
			log.warn("Exception, msg: {}",e.getMessage());
		}
		return new ArrayList<>();
	}
	
	public List<User> getByName(String name){
		log.info("get user by name: {}",name);
		try {
			return userRepository.findByName(name).get();
		} catch (InterruptedException | ExecutionException e) {
			log.warn("Exception, msg: {}",e.getMessage());
		}
		return new ArrayList<>();
	}
	
	public User getByEmail(String email) {
		log.info("get user by email: {}",email);
		try {
			return userRepository.findByEmail(email).get();
		} catch (InterruptedException | ExecutionException e) {
			log.warn("Exception, msg: {}",e.getMessage());
		}
		return new User();
	}
	
	public List<User> getAll(){
		return userRepository.findAll();
	}

	public User update(User user) {
		return userRepository.save(user);
	}
	
	public boolean remove(long id) {
		try {
			userRepository.deleteById(id);
			return true;
		} catch (Exception e) {
			return false;
		}
		
	}
}
