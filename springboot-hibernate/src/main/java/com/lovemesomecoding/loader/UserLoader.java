package com.lovemesomecoding.loader;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lovemesomecoding.model.Address;
import com.lovemesomecoding.model.Role;
import com.lovemesomecoding.model.User;
import com.lovemesomecoding.service.UserService;
import com.lovemesomecoding.utils.ObjectUtils;
import com.lovemesomecoding.utils.RandomGeneratorUtils;

@Component
public class UserLoader {
	
	private Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private UserService userService;
	
	@PostConstruct
	public void loadUsers() {
		log.info("loadUsers...");
		
		User user = new User();
		user.setUid(RandomGeneratorUtils.getUserUuid());
		user.setEmail("folaukaveinga@gmail.com");
		user.setPassword("Test1234!");
		Address address = new Address();
		address.setCity("El Segundo");
		address.setState("CA");
		address.setStreet("Rosescrans");
		address.setZip("90305");
		
		user.setAddress(address);
		
		log.info("pre persist: {}",ObjectUtils.toJson(user));
		
		Role role = new Role();
		role.setAuthority(Role.ADMIN);
		role.addUser(user);
		user.addRole(role);
		
		role = new Role();
		role.setAuthority(Role.USER);
		role.addUser(user);
		user.addRole(role);
		
		role = new Role();
		role.setAuthority(Role.MANAGER);
		role.addUser(user);
		user.addRole(role);
		
		user = userService.create(user);
		
		log.info("post persist: {}",ObjectUtils.toJson(user));
		
		
		user = new User();
		user.setUid(RandomGeneratorUtils.getUserUuid());
		user.setEmail("folaukaveinga+1@gmail.com");
		user.setPassword("Test1234!");
		address = new Address();
		address.setCity("El Segundo");
		address.setState("CA");
		address.setStreet("Rosescrans");
		address.setZip("90305");
		
		user.setAddress(address);
		
		log.info("pre persist: {}",ObjectUtils.toJson(user));
		
		role = new Role();
		role.setAuthority(Role.USER);
		role.addUser(user);
		user.addRole(role);
		
		user = userService.create(user);
		
		log.info("post persist: {}",ObjectUtils.toJson(user));
		
		user.setEmail("folaukaveinga+100+update@gmail.com");
		user.setPassword("Test1234!");
		
		user =userService.update(user);
		
		log.info("post update: {}",ObjectUtils.toJson(user));
		
	}
}
