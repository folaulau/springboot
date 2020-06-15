package com.folaukaveinga.testing.user;

import java.time.LocalDate;
import java.util.List;

import com.folaukaveinga.testing.plan.Plan;

public interface UserService {

	public User save(User user);

	public User signUp(User user);

	public User update(User user);

	public User getById(long id);

	public List<User> getByLastName(String name);

	public User getByEmail(String email);

	public List<User> getAll();

	public boolean remove(long id);

	public int getAge(LocalDate dob);

    public Plan signUpForPlan(Plan plan);

}
