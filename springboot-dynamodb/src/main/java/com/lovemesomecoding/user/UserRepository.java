package com.lovemesomecoding.user;

import java.util.List;

public interface UserRepository {

	User create(User user);
	
	User getById(String id);
	
	List<User> getAllUser();
	
	boolean createTable();
}
