package com.folaukaveinga.testing.unit;

import java.time.LocalDate;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Timed;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.folaukaveinga.testing.user.User;
import com.folaukaveinga.testing.user.UserRepository;
import com.folaukaveinga.testing.user.UserService;



@RunWith(SpringRunner.class)
@DataJpaTest
//@ContextConfiguration(classes = {UserService.class,UserRepository.class})
public class UserServiceTest {

	@Autowired
	private UserService userService;
	
	@Autowired
	private UserRepository userRepository;

	@Before
	public void setup() {
		userService = new UserService(userRepository);
	}

	//@Timed(millis = 2000)
	@Test
	public void testGetDOB() throws Exception {
		//Thread.sleep(5000);
		int age = userService.getAge(LocalDate.now().plusYears(-5));
		System.out.println("age="+age);
	}
	
	
	@Test
	public void testGet() throws Exception {
		//Thread.sleep(5000);
		User user = userService.get(1);
		System.out.println("age="+user.toJson());
	}

}