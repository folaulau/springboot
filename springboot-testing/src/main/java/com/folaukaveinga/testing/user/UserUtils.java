package com.folaukaveinga.testing.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public interface UserUtils {
	
	static final Logger log = LoggerFactory.getLogger(UserUtils.class);

	public static void validateUser(User user, UserValidatorService userValidatorService) {
		log.info("user={}",user.toJson());
		
		userValidatorService.sayHi();
	}
}
