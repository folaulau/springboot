package com.folaukaveinga.testing.unit;

import static org.mockito.ArgumentMatchers.same;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;

import org.apache.commons.lang3.RandomUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Timed;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;

import com.folaukaveinga.testing.user.User;
import com.folaukaveinga.testing.user.UserRepository;
import com.folaukaveinga.testing.user.UserService;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceMockitoTest {

	private UserService userService;

	@Mock
	private UserRepository userRepository;

	@Mock
	private Logger log;

	@Before
	public void setup() {
		userService = new UserService(userRepository);

		ReflectionTestUtils.setField(userService, "log", log);
	}

	@Test
	public void testSave() throws Exception {
		log.info("testSave()");
		User user = new User("kinga", 21, "kinga@gmail.com");

		log.info(user.toJson());

		userService.save(user);

		verify(userRepository).saveAndFlush(same(user));
	}

	@Test
	public void testSaveWithException() throws Exception {
		log.info("testRepoSave()");
		User user = new User("kinga", 21, "kinga" + RandomUtils.nextInt(1, Integer.MAX_VALUE) + "@gmail.com");

		when(userRepository.saveAndFlush(user))
				.thenThrow(new RuntimeException("An error occurred"));

		userService.save(user);

		verify(userRepository).saveAndFlush(same(user));
		verify(log).error("An error occurred");
	}

	@Timed(millis = 2000)
	@Test
	public void testGetDOB() throws Exception {
		log.info("testGetDOB()");
		Thread.sleep(5000);
		int age = userService.getAge(LocalDate.now().plusYears(-5));

		log.info("age={}", age);
	}

}