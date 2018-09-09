package com.kaveinga.loader;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.kaveinga.account.Account;
import com.kaveinga.user.User;
import com.kaveinga.user.UserService;
import com.kaveinga.utility.RandomGeneratorUtils;

@Component
public class UserLoader {
	
	private Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private UserService userService;
	
	void load() {
		userService.removeAll();
		for(int i=0;i<10;i++) {
			User user = new User();
			user.setFirstName(RandomGeneratorUtils.getAlphaNumeric(5));
			user.setLastName(RandomGeneratorUtils.getAlphaNumeric(5));
			user.setEmail(RandomGeneratorUtils.getAlphaNumeric(10)+"@gmail.com");
			user.setAge(RandomGeneratorUtils.getInteger());
			
			Account account = new Account(Long.parseLong(RandomGeneratorUtils.getIntegerWithin(1, 10)+""));
			user.setAccount(account);
			
			user = this.userService.create(user);
			log.info(user.toJson());
		}
	}
}
