package com.folaukaveinga.springboot.service;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
		user = userRepository.saveAndFlush(user);
		log.info("user saved");
		log.info(user.toString());
		return user;
	}
	
	// cache this query
	//@Cacheable(value = "user_id")
	public User get(long id){
		log.info("get user by id: {}",id);
		return userRepository.findById(id).get();
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
	//@Cacheable(value = "user_name")
	public User getByName(String name){
		log.info("get user by name: {}",name);
		try {
			return userRepository.findByName(name).get();
		} catch (InterruptedException | ExecutionException e) {
			log.warn("Exception, msg: {}",e.getMessage());
		}
		return new User();
	}
	
	public List<User> getAll(Pageable page){
		Page<User> p = userRepository.findAll(page);
		List<User> users = p.getContent();
		return users;
	}

	public User update(User user) {
		return userRepository.save(user);
	}
	
	
	public List<User> search(int yr, Pageable page){
		@SuppressWarnings("unchecked")
		Page<User> p = userRepository.findAll(UserSpecs.isUserYoungerThan20(yr), page);
		List<User> users = p.getContent();
		return users;
	}
	
}
