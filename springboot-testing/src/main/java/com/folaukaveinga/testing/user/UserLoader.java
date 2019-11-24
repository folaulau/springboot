package com.folaukaveinga.testing.user;

import java.util.Date;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.folaukaveinga.testing.utility.ConstantUtils;
import com.folaukaveinga.testing.utility.ObjectUtils;
import com.folaukaveinga.testing.utility.RandomGeneratorUtils;

@Component
public class UserLoader {

	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private UserRepository userRepository;

	@PostConstruct
	public void init() {
		log.info("loading users...");
		for (int i = 0; i < 10; i++) {
			User user = ConstantUtils.generateUser();

			user = addDays(user);

			user = userRepository.saveAndFlush(user);

			log.info("user={}", ObjectUtils.toJson(user));
		}
		log.info("done loading users!");
	}

	private User addDays(User user) {
		int numOfOffDays = RandomGeneratorUtils.getIntegerWithin(1, 10);
		for (int i = 0; i < numOfOffDays; i++) {
			user.addOffDay(DateUtils.addDays(new Date(), RandomGeneratorUtils.getIntegerWithin(1, 350)));
		}
		return user;
	}
}
