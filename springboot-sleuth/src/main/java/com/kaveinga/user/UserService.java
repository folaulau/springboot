package com.kaveinga.user;

import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserService {

	// @NewSpan
	public User getById(long id) {
		log.debug("getById({})", id);
		User user = new User();
		user.setId(id);
		user.setFirstName("Folau");
		user.setLastName("Kaveinga");
		return user;
	}
}
