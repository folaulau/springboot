package com.folaukaveinga.testing.integration;

import static org.junit.Assert.*;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.folaukaveinga.testing.user.User;
import com.folaukaveinga.testing.user.UserRepository;

@RunWith(SpringRunner.class)
//@DataJpaTest
@SpringBootTest
public class UserRepositoryTest {

	//@Autowired
	//private TestEntityManager entityManager;

	@Autowired
	private UserRepository userRepository;

	@Ignore
	@Test
	public void loadData() {
		userRepository.save(new User("Jane Doe", 23, "janedoe@gmail.com"));
		userRepository.save(new User("John Doe", 23, "johndoe@gmail.com"));
		userRepository.save(new User("Joe Doe", 23, "joedoe@gmail.com"));
	}
	
	@Test
	public void testSave() {
		User user = new User("Jj Doe", 23, "jjdoe@gmail.com");
		User savedUser = userRepository.save(user);
		assertEquals(savedUser, user);
		savedUser.setAge(34);
		assertNotEquals(savedUser, user);
	}
	
	@Test
	public void testFindByAge() throws InterruptedException, ExecutionException {
		int age = 23;
		Future<List<User>> savedUsers = userRepository.findByAge(age);
		assertNotNull(savedUsers);
		assertNotEquals(savedUsers.get().size(), 0);
		
		savedUsers.get().forEach((user)->{
			System.out.println(user.toString());
		});
	}
	
	@Test
	public void testFindByName() throws InterruptedException, ExecutionException {
		String name = "John Doe";
		Future<List<User>> savedUsers = userRepository.findByName(name);
		assertNotNull(savedUsers);
		assertNotEquals(savedUsers.get().size(), 0);
		
		savedUsers.get().forEach((user)->{
			System.out.println(user.toString());
		});
	}
	
	@Test
	public void testFindByEmail() throws InterruptedException, ExecutionException {
		String email = "janedoe@gmail.com";
		Future<User> savedUser = userRepository.findByEmail(email);
		assertNotNull(savedUser);
		assertNotEquals(savedUser.get().getId(), 0);
		System.out.println(savedUser.get().toString());
	}
}
