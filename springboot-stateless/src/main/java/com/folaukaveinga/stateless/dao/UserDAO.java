package com.folaukaveinga.stateless.dao;

import java.util.List;

import com.folaukaveinga.stateless.model.User;

public interface UserDAO {
	
	public User saveUser(User user);
	public User getUserById(long id);
	public List<User> getAllUsers();
	public void removeUser(User user);
}
