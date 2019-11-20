package com.folaukaveinga.testing.user;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.folaukaveinga.testing.utility.ConstantUtils;
import com.folaukaveinga.testing.utility.ObjectUtils;

@Component
public class UserLoader {
	
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private UserRepository userRepository;

	@PostConstruct
	public void init() {
		log.info("loading users...");
		for(int i=0;i<10;i++) {
			User user = ConstantUtils.getMember();
			user = userRepository.saveAndFlush(user);
			
			log.info("user={}",ObjectUtils.toJson(user));
		}
		log.info("done loading users!");
	}
}
