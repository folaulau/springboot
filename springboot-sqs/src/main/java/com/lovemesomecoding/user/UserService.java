package com.lovemesomecoding.user;

public interface UserService {
	
	User create(User user);
	
	User getById(Long id);
	
	void sendWelcomeEmail(User user);
}
