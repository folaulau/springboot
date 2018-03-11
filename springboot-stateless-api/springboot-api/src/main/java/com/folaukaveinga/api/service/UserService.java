package com.folaukaveinga.api.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Service;

import com.folaukaveinga.api.dao.UserDAO;
import com.folaukaveinga.api.model.User;

@Service
public class UserService implements UserDAO {
	
	List<User> users = new ArrayList<>();
	
	{
		users.add(new User(1,"user@gmail.com","$2a$10$OKzfs9hLkJMD5lka8ha11e41dbfzAHTvzqPuf/.GbW.5ja4hp.ola", Arrays.asList("ROLE_USER")));
		users.add(new User(2,"admin@gmail.com","$2a$10$86jJXgGwQvUEVVHkBiRl.uVHOREUbsf9ptE0ctc.GNqggwDeCAhX6", Arrays.asList("ROLE_USER","ROLE_ADMIN")));
		users.add(new User(3,"disabled@gmail.com","$2a$10$OKzfs9hLkJMD5lka8ha11e41dbfzAHTvzqPuf/.GbW.5ja4hp.ola", Arrays.asList("ROLE_DISABLED")));
	}

	@Override
	public User saveUser(User user) {
		return user;
	}

	@Override
	public User getUserById(long id) {
		for(User user : users) {
			if(user.getId()==id) {
				return user;
			}
		}
		return new User();
	}

	@Override
	public List<User> getAllUsers() {
		return users;
	}

	@Override
	public void removeUser(User user) {
		Iterator<User> it = users.iterator();
		while (it.hasNext()) {
			User u = it.next();
			if(user.getId()==u.getId()) {
				it.remove();
			}
		}
	}

	@Override
	public User getUserByEmail(String email) {
		for(User user : users) {
			if(user.getEmail().equals(email)) {
				return user;
			}
		}
		return new User();
	}

}
