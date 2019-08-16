package com.lovemesomecoding.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImp implements UserService {

	private Logger log = LoggerFactory.getLogger(this.getClass());

	@Override
	public User create(User user) {
		return null;
	}

	@Override
	public User getById(Long id) {
		// TODO Auto-generated method stub

		return null;
	}

	
}
