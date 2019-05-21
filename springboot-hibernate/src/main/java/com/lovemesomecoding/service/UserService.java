package com.lovemesomecoding.service;

import com.lovemesomecoding.model.User;

public interface UserService {
	
	User create(User user);
	
	User getById(Long id);
	
	User update(User user);
	
	User delete(User user);
}
