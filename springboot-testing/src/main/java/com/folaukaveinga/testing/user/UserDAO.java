package com.folaukaveinga.testing.user;

import java.util.List;

public interface UserDAO {

	User save(User user);
	
	User getById(long id);
	
	List<User> getByAge(int age);
}
