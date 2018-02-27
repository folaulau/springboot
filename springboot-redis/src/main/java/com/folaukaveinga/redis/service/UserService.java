package com.folaukaveinga.redis.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.folaukaveinga.redis.model.User;
import com.folaukaveinga.redis.repository.UserRepository;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;
	
	public User saveUser(User user) {
		return userRepository.add(user);
	}
	
	public User findUser(long id) {
		return userRepository.findUser(id);
	}

	public Map<Object, Object> findAllUsers() {
		return userRepository.findAllUsers();
	}
	
	public long removeUser(long id) {
		return userRepository.delete(id);
	}
}
