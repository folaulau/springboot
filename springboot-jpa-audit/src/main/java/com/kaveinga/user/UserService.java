package com.kaveinga.user;

import java.util.List;

public interface UserService {

	User create(User user);
	User update(User user);
	User getById(Long id);
	void remove(Long id);
	List<User> getAll();
}
