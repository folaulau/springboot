package com.folaukaveinga.springboot.database;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.folaukaveinga.springboot.domain.BankAccount;
import com.folaukaveinga.springboot.domain.CreditCard;
import com.folaukaveinga.springboot.domain.User;
import com.folaukaveinga.springboot.service.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {

	@Autowired
	private UserService userService;
	
	@Test
	public void testSave() {
		System.out.println("test save");
		User user = new User("Folau",12, "folaukaveinga@gmail.com");
		CreditCard creditCard = new CreditCard();
		creditCard.setBrand("VISA");
		creditCard.setType("credit");
		creditCard.setLast4("1234");
		user.addPaymentMethod(creditCard);
		BankAccount bankAccount = new BankAccount();
		bankAccount.setType("credit");
		bankAccount.setLast4("1234");
		bankAccount.setBank("CHASE");
		bankAccount.setName("Folau Kaveinga");
		user.addPaymentMethod(bankAccount);
		System.out.println("USER");
		System.out.println(user.toJson());
		
		user = userService.save(user);
		
		System.out.println(user.toJson());
//		assertEquals(1, user.getId());
//		
//		user = userService.save(new User("Folaulau",5, "folaudev@gmail.com"));
//		assertEquals(1, user.getId());
//		
//		user = userService.save(new User("Lisa",10, "efinau10@gmail.com"));
//		assertEquals(1, user.getId());
		
		System.out.println("save done!");
	}
	
	@Test
	public void testGetAll() {
		System.out.println("test GetAll");
		//userService.getAll().forEach(System.out::println);
		System.out.println("getAll done!");
	}
	
	@Test
	public void testGetByName() {
		System.out.println("test getByName");
		User user = userService.getByName("Folau");
		System.out.println(user.toString());
		System.out.println("getByName done!");
	}
	
	@Test
	public void testGetByAge() {
		System.out.println("test getByAge");
		User user = userService.getByAge(10);
		System.out.println(user.toString());
		System.out.println("getByAge done!");
	}
	
	@Test
	public void testGetByEmail() {
		System.out.println("test getByEmail");
		//User user = userService.getByEmail("folaudev@gmail.com");
		//System.out.println(user.toString());
		System.out.println("getByEmail done!");
	}

}
