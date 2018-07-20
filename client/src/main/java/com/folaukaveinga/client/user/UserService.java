package com.folaukaveinga.client.user;

import java.util.List;
import java.util.UUID;

public interface UserService {
	
	User create(User user);
	
	User update(User user);
	
	User getById(Integer id);
	
	List<User> getAll();
}
