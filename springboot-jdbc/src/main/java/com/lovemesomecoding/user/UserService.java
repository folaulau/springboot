package com.lovemesomecoding.user;

import java.util.List;
import java.util.Optional;

import com.lovemesomecoding.dto.SessionDTO;
import com.lovemesomecoding.dto.SignupRequest;

public interface UserService {
	
	User create(User user);
	
	void setup();
	
	User getById(Long id);
	
	User update(User user);
	
	List<User> getAll();
	
	boolean delete(User user);
}
