package com.folaukaveinga.springboot.service;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.folaukaveinga.springboot.domain.User;
import com.folaukaveinga.springboot.repository.UserRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

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
	
	// cache this query
	@Cacheable(value = "user_id")
	public User get(long id){
		log.info("get user by id: {}",id);
		return userRepository.getOne(id);
	}
	
	public User getByAge(int age){
		log.info("get user by age: {}",age);
		try {
			return userRepository.findByAge(age).get();
		} catch (InterruptedException | ExecutionException e) {
			log.warn("Exception, msg: {}",e.getMessage());
		}
		return new User();
	}
	@Cacheable(value = "user_name")
	public User getByName(String name){
		log.info("get user by name: {}",name);
		try {
			return userRepository.findByName(name).get();
		} catch (InterruptedException | ExecutionException e) {
			log.warn("Exception, msg: {}",e.getMessage());
		}
		return new User();
	}
	
	public Mono<User> getByEmail(String email) {
		log.info("get user by email: {}",email);
		try {
			return userRepository.findByEmail(email);
		} catch (Exception e) {
			log.warn("Exception, msg: {}",e.getMessage());
			return null;
		}
		
	}
	
	public Flux<User> getByLastName(String lastname) {
		log.info("get user by lastname: {}",lastname);
		try {
			return userRepository.findByLastName(lastname);
		} catch (Exception e) {
			log.warn("Exception, msg: {}",e.getMessage());
			return null;
		}
	}
	
	@Cacheable(value = "user_all")
	public List<User> getAll(){
		return userRepository.findAll();
	}

	public User update(User user) {
		return userRepository.save(user);
	}
}
