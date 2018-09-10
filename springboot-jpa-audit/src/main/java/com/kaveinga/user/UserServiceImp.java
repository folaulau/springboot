package com.kaveinga.user;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import com.kaveinga.events.UserEvent;

@Service
public class UserServiceImp implements UserService {

	private Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ApplicationEventPublisher applicationEventPublisher;

	@Override
	public User create(User user) {
		log.info("create(..)");
		applicationEventPublisher.publishEvent(new UserEvent(user));
		return userRepository.saveAndFlush(user);
	}

	@Override
	public User update(User user) {
		log.info("update(..)");
		return userRepository.saveAndFlush(user);
	}

	@Override
	public User getById(Long id) {
		log.info("getById(..)");
		return userRepository.getOne(id);
	}

	@Override
	public void remove(Long id) {
		log.info("remove(..)");
		userRepository.deleteById(id);
	}

	@Override
	public List<User> getAll() {
		log.info("getAll()");
		return userRepository.findAll();
	}

	@Override
	public void removeAll() {
		log.info("removeAll()");
		userRepository.deleteAll();
	}

}
