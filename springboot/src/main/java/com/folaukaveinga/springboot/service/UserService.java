package com.folaukaveinga.springboot.service;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.folaukaveinga.springboot.domain.User;
import com.folaukaveinga.springboot.ldap.repository.UserLdapRepository;

@Service
public class UserService {

	@Autowired
	private UserLdapRepository userLdapRepository;
	
	public User getUserByUid(String uid) {
		return userLdapRepository.getUserByDn(uid);
	}
	
	public List<User> getAllUsers() {
		User user = new User();
		user.setEmail(RandomStringUtils.randomAlphanumeric(15)+"@gmail.com");
		user.setFirstName(RandomStringUtils.randomAlphanumeric(10));
		user.setLastName(RandomStringUtils.randomAlphanumeric(10));
		user.setDisplayName(RandomStringUtils.randomAlphanumeric(10));
		user.setPassword("test12");
		user.setUid(RandomStringUtils.randomAlphanumeric(10));
		
		userLdapRepository.create(user);
		return userLdapRepository.getAllUsers();
	}
}
