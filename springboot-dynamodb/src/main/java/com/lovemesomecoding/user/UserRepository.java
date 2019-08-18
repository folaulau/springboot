package com.lovemesomecoding.user;

import java.util.List;

public interface UserRepository {

	User create(User user);
	
	boolean tranferBalance(double amount, User userA, User userB);
	
	boolean tranferBalance(double amount, String userIdA, String userIdB);
	
	User getById(String id);
	
	List<User> getAllUser();
	
	boolean createTable();
	
	boolean deleteTable();
}
