package com.folaukaveinga.testing.user;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.folaukaveinga.testing.user.User;
import com.folaukaveinga.testing.user.UserRepository;
import com.folaukaveinga.testing.utility.ConstantUtils;
import com.folaukaveinga.testing.utility.ObjectUtils;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserRepositoryTest {

	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private UserRepository userRepository;

	private String firstName;
	private int age;
	private String email;

	@Before
	public void setup() {

		for (int i = 0; i < 5; i++) {
			User user = ConstantUtils.generateUser();
			user = userRepository.saveAndFlush(user);
			firstName = user.getFirstName();
			age = user.getAge();
			email = user.getEmail();

//			log.info("user={}",ObjectUtils.toJson(user));
		}

		System.out.println("\n");
	}

	@Transactional
	@Test
	public void testSave() {
		log.info("testSave({})", age);
		User user = ConstantUtils.generateUser();
		User savedUser = userRepository.saveAndFlush(user);

		assertNotNull(savedUser);

		log.info("savedUser==user -> {}", savedUser == user);

		assertEquals(user, savedUser);

		log.info("user={}", ObjectUtils.toJson(user));
		log.info("savedUser={}", ObjectUtils.toJson(savedUser));

		log.info("\n\ntestSave passed\n");
	}

//	@Transactional
//	@Test
//	public void testFindByAge() throws InterruptedException, ExecutionException {
//		log.info("testFindByAge({})", age);
//		List<User> savedUsers = userRepository.findByAge(age);
//		assertNotNull(savedUsers);
//		assertNotEquals(savedUsers.size(), 0);
//
//		savedUsers.forEach((user) -> {
//			System.out.println(user.toString());
//		});
//		
//		log.info("\n\ntestFindByAge passed\n");
//	}

	@Transactional
	@Test
	public void testFindByName() throws InterruptedException, ExecutionException {
		log.info("testFindByName({})", firstName);
		String name = firstName;
		Future<List<User>> savedUsers = userRepository.findByLastName(name);

		assertNotNull(savedUsers);
		assertNotEquals(savedUsers.get().size(), 0);

		savedUsers.get().forEach((user) -> {
			System.out.println(user.toString());
		});

		log.info("\n\ntestFindByName passed\n");
	}

	@Transactional
	@Test
	public void testFindByEmail() throws InterruptedException, ExecutionException {
		log.info("testFindByEmail({})", email);
		User savedUser = userRepository.findByEmail(email);

		assertNotNull(savedUser);
		assertNotEquals(savedUser.getId().longValue(), 0);

		System.out.println(savedUser.toString());

		log.info("\n\ntestFindByEmail passed\n");
	}

}