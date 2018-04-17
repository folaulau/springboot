package com.folaukaveinga.springboot.database;

import static org.junit.Assert.assertEquals;

import java.util.UUID;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.folaukaveinga.springboot.domain.Account;
import com.folaukaveinga.springboot.domain.User;
import com.folaukaveinga.springboot.service.AccountService;
import com.folaukaveinga.springboot.service.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AccountServiceTest {

	@Autowired
	private AccountService accountService;
	
	@Test
	public void testSave() {
		Account account = new Account();
		account.setName("Folau");
		account = accountService.save(account);
		System.out.println(account);
		System.out.println(account.getId().toString());
	}
}
