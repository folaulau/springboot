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
		boolean result = userLdapRepository.authenticate(uid, "test12");
		System.out.println("result: "+result);
		return userLdapRepository.getUserByDn(uid);
	}

	public boolean saveUser(User user) {
		return userLdapRepository.create(user);
	}
	
	
	public List<User> getAllUsers() {
		return userLdapRepository.getAllUsers();
	}
	
	public List<User> getAllUsersByDepartment(String department) {
		return userLdapRepository.getAllUsersByDeparment(department);
	}
	
	public boolean deleteUser(String uid) {
		return userLdapRepository.delete(uid);
	}
}
