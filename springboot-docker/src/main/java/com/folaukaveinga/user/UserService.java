package com.folaukaveinga.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	public User save(User user) {
		return this.userRepository.save(user);
	}
	
	public User findById(Long id) {
		return this.userRepository.findById(id).get();
	}
	
	public List<User> getAll(){
		return this.userRepository.findAll();
	}
}
