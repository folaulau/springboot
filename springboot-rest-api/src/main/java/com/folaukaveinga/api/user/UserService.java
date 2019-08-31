package com.folaukaveinga.api.user;

import java.util.List;
import java.util.UUID;

public interface UserService {
	
	User create(User user);
	
	User update(User user);
	
	User getById(Integer id);
	
	User getByUuid(UUID uuid);
	
	List<User> getAll();
}
