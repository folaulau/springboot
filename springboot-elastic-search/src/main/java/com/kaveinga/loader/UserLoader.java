package com.kaveinga.loader;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kaveinga.user.User;
import com.kaveinga.user.UserService;

@Repository
public class UserLoader {

	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private UserService userService;
	
	@PostConstruct
	public void init() {
		log.info("Userloader.init()");
		for (int i = 1; i <= 300 ; i++) {
			User user = new User();
			user.setId(new Long(i));
			user.setName(RandomStringUtils.randomAlphanumeric(10));
			user.setEmail(RandomStringUtils.randomAlphanumeric(15));
			if(userService.doesExist(new Long(i))==false) {
				userService.save(user);
			}
		}
	}
}
