package com.folaukaveinga.springboot.database;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.folaukaveinga.springboot.domain.User;
import com.folaukaveinga.springboot.service.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {

	@Autowired
	private UserService userService;
	
	@Test
	public void save() {
		User user = userService.save(new User("Folau",12));
		assertEquals(1, user.getId());
	}

}
