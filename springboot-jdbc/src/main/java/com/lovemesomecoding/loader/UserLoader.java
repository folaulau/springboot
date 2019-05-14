package com.lovemesomecoding.loader;

import java.util.List;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lovemesomecoding.user.User;
import com.lovemesomecoding.user.UserService;
import com.lovemesomecoding.utils.ObjectUtils;
import com.lovemesomecoding.utils.RandomGeneratorUtils;

@Component
public class UserLoader {
	
	private Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private UserService userService;
	
	@PostConstruct
	public void init() {
		userService.setup();
		
		loadUsers();
		
		List<User> allUsers = userService.getAll();
		
		log.info("allUsers={}",ObjectUtils.toJson(allUsers));
		
		User deleteUser = allUsers.get(RandomGeneratorUtils.getIntegerWithin(0, allUsers.size()));
	
		this.delete(deleteUser);
		
		allUsers = userService.getAll();
		
		log.info("allUsers={}",ObjectUtils.toJson(allUsers));
	}
	

	public void loadUsers() {
		log.info("loadUsers...");
		
		User user = new User();
		user.setUid(RandomGeneratorUtils.getUserUuid());
		user.setEmail("folaukaveinga@gmail.com");
		user.setPassword("test");
		
		log.info("pre persist: {}",ObjectUtils.toJson(user));
		
		user = userService.create(user);
		
		log.info("post persist: {}",ObjectUtils.toJson(user));
		
		user = userService.getById(user.getId());
		
		log.info("loaded user: {}",ObjectUtils.toJson(user));
		
		user.setEmail("folaukaveinga+"+RandomGeneratorUtils.getInteger()+"@gmail.com");
		
		log.info("preupdate user: {}",ObjectUtils.toJson(user));
		
		user = userService.update(user);
		
		log.info("updated user: {}",ObjectUtils.toJson(user));
		
		
	}
	
	private void delete(User user) {
		userService.delete(user);
	}
}
