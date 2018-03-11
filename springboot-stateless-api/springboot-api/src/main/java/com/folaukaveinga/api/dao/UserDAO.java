package com.folaukaveinga.api.dao;

import java.util.List;

import com.folaukaveinga.api.model.User;

public interface UserDAO {
	
	public User saveUser(User user);
	public User getUserById(long id);
	public User getUserByEmail(String email);
	public List<User> getAllUsers();
	public void removeUser(User user);
}
