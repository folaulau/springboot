package com.folaukaveinga.ldap.user;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.folaukaveinga.ldap.domain.LdapUser;
import com.folaukaveinga.ldap.service.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {

	@Autowired
	private UserService userService;
	
	@Test
	public void testLogin() {
		boolean result = userService.login("4894", "Test123!");
		assertEquals(true, result);
	}
	
	@Test
	public void testGetUser() {
		LdapUser ldapUser = userService.getUser("4894");
		System.out.println(ldapUser.toString());
		assertEquals("4894", ldapUser.getId());
		
	}

}
