package com.lovemesomecoding.user;

import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lovemesomecoding.utils.ObjectUtils;
import com.lovemesomecoding.utils.RandomGeneratorUtils;

@Component
public class UserLoader {
	
	@Autowired
	private UserRepository userRepository;

	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	@PostConstruct
	public void init() {
		boolean userTableCreated = userRepository.createTable();
		
		log.debug("userTableCreated={}",userTableCreated);
		
		User user = new User();
		user.setUuid(RandomGeneratorUtils.getUserUuid());
		user.setFirstName("Folau");
		user.setLastName("Kaveinga");
		user.setCreatedAt(new Date());
		user.setPhoneNumber("3109934731");
		user.setBalance(RandomGeneratorUtils.getLongWithin(100, 100000));
		
		userRepository.create(user);
		
		List<User> users = userRepository.getAllUser();
		
		log.debug("users={}",ObjectUtils.toJson(users));
		
		User existingUser  = userRepository.getById(users.get(RandomGeneratorUtils.getIntegerWithin(0, users.size())).getUuid());
		
		log.debug("existingUser={}",ObjectUtils.toJson(existingUser));
	}
}
