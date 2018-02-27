package com.folaukaveinga.stateless.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Service;

import com.folaukaveinga.stateless.dao.UserDAO;
import com.folaukaveinga.stateless.model.User;

@Service
public class UserService implements UserDAO {
	
	List<User> users = new ArrayList<>();
	
	{
		users.add(new User(1));
		users.add(new User(2));
		users.add(new User(3));
		users.add(new User(4));
	}

	@Override
	public User saveUser(User user) {
		user.setStatus("saved");
		return user;
	}

	@Override
	public User getUserById(long id) {
		for(User user : users) {
			if(user.getId()==id) {
				return user;
			}
		}
		return null;
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

}
