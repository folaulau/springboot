package com.folaukaveinga.loader;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.folaukaveinga.address.Address;
import com.folaukaveinga.role.Role;
import com.folaukaveinga.user.User;
import com.folaukaveinga.user.UserService;
import com.folaukaveinga.utils.ObjectUtils;
import com.folaukaveinga.utils.PasswordUtils;
import com.folaukaveinga.utils.RandomGeneratorUtils;

@Component
public class UserLoader {
	
	private Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private UserService userService;
	
	@PostConstruct
	public void loadUsers() {
		log.info("loadUsers...");
		
		User user = new User();
		user.setUid(RandomGeneratorUtils.getUuid());
		user.setAge(21);
		user.setName("Folau");
		user.setEmail("folaukaveinga@gmail.com");
		user.setPassword(PasswordUtils.hashPassword("test"));
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
		user.setUid(RandomGeneratorUtils.getUuid());
		user.setAge(22);
		user.setName("Folau");
		user.setEmail("folaukaveinga+1@gmail.com");
		user.setPassword(PasswordUtils.hashPassword("test"));
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
		
	}
}
