package com.kaveinga.user;

public interface UserService {

	User create(User user);
	User update(User user);
	User getById(long id);
	void delete(User user);
	void deleteById(long id);
}
