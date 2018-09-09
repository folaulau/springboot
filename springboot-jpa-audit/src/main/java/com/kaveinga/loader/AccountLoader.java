package com.kaveinga.loader;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.kaveinga.account.Account;
import com.kaveinga.account.AccountService;
import com.kaveinga.user.User;
import com.kaveinga.user.UserService;
import com.kaveinga.utility.RandomGeneratorUtils;

@Component
public class AccountLoader {
	
	private Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private AccountService accountService;
	
	@Autowired
	private UserLoader userLoader;
	
//	@PostConstruct
//	void init() {
//		log.info("init()");
//		load();
//		
//		userLoader.load();
//	}
	
	void load() {
		for(int i=0;i<10;i++) {
			Account account = new Account();
			account.setBalance(RandomGeneratorUtils.getInteger());
			//account.addUser(new User(Long.parseLong(RandomGeneratorUtils.getIntegerWithin(1, 10)+"")));
			account = accountService.create(account);
			log.info(account.toJson());
		}
	}
}
