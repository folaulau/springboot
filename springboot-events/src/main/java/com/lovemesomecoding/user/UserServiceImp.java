package com.lovemesomecoding.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lovemesomecoding.event.publisher.UserEventPublisher;
import com.lovemesomecoding.utils.ObjectUtils;

@Service
public class UserServiceImp implements UserService {

	private Logger log = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private UserEventPublisher userEventPublisher;

	@Override
	public User create(User user) {
		log.info("create={}", ObjectUtils.toJson(user));

		userEventPublisher.processUserCreate(user);

		return user;
	}

	@Override
	public User update(User user) {
		log.info("update={}", ObjectUtils.toJson(user));

		userEventPublisher.processUserUpdate(user);

		return user;
	}

}
