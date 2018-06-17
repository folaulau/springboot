package com.kaveinga.user;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private UserSearchRepository userSearchRepository;
	
	public User save(User user) {
		log.info("save(..)");
		return userSearchRepository.save(user);
	}
	
	public User get(Long id) {
		log.info("get(..)");
		return this.userSearchRepository.findById(id).get();
	}
	
	public User update(User user) {
		log.info("update(..)");
		return userSearchRepository.save(user);
	}

	public Boolean delete(Long id) {
		log.info("delete(..)");
		this.userSearchRepository.delete(new User(id));
		return true;
	}

	public List<User> getAll() {
		// TODO Auto-generated method stub
		List<User> users = new ArrayList<>();
		userSearchRepository.findAll().forEach(users::add);
		return users;
	}
	
	public boolean doesExist(Long id) {
		return userSearchRepository.existsById(id);
	}

}
