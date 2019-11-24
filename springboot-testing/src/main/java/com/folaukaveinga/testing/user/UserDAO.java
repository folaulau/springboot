package com.folaukaveinga.testing.user;

import java.util.List;

public interface UserDAO {

	User save(User user);
	
	User getById(long id);
	
	List<User> getByAge(int age);
	
	String getEmailById(long id);
	
	Long getIdByEmail(String email);

	List<User> getAll();

	User getByEmail(String email);

	boolean deleteById(long id);
	
	List<User> getByLastName(String lastName);
}
