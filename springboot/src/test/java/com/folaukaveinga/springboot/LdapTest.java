package com.folaukaveinga.springboot;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.folaukaveinga.springboot.ldap.repository.UserLdapRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LdapTest {
	
	@Autowired
	private UserLdapRepository userLdapRepository;
	
	@Test
	public void testGetAllUsers() {
		System.out.println("test get all users!");
		userLdapRepository.getAllUsers().forEach((user)->{
			System.out.println(user.toString());
		});
		System.out.println("done!");
	}
	
	@Test
	public void testCreate() {
		System.out.println("test get all users!");
		//userLdapRepository.create("folauk", "test12");
	}

}
