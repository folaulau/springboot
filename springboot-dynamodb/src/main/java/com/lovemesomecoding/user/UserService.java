package com.lovemesomecoding.user;

import java.util.List;

public interface UserService {

	User create(User user);
	
	List<User> getAllUsers();
}
