package com.folaukaveinga.springboot.paymentmethod;

import static org.junit.Assert.assertEquals;

import java.util.UUID;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.folaukaveinga.springboot.domain.Account;
import com.folaukaveinga.springboot.domain.BankAccount;
import com.folaukaveinga.springboot.domain.CreditCard;
import com.folaukaveinga.springboot.domain.PaymentMethod;
import com.folaukaveinga.springboot.domain.User;
import com.folaukaveinga.springboot.repository.BankAccountRepository;
import com.folaukaveinga.springboot.repository.CreditCardRepository;
import com.folaukaveinga.springboot.repository.PaymentMethodRepository;
import com.folaukaveinga.springboot.service.AccountService;
import com.folaukaveinga.springboot.service.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PaymentMethodTest {

	@Autowired
	private PaymentMethodRepository paymentMethodRepository;
	
	@Autowired
	private CreditCardRepository creditCardRepository;
	
	@Autowired
	private BankAccountRepository bankAccountRepository;
	
	@Test
	public void testSaveCreditCard() {
		CreditCard creditCard = new CreditCard();
		creditCard.setBrand("VISA");
		creditCard.setType("credit");
		creditCard.setLast4("1234");
		creditCard = creditCardRepository.save(creditCard);
		
		System.out.println(creditCard.toString());
		
	}
	
	@Test
	public void testSaveBankAccount() {
		BankAccount bankAccount = new BankAccount();
		bankAccount.setType("credit");
		bankAccount.setLast4("1234");
		bankAccount.setBank("CHASE");
		bankAccount.setName("Folau Kaveinga");
		bankAccount = bankAccountRepository.save(bankAccount);
		
		System.out.println(bankAccount.toString());
		
	}
	
	@Test
	public void testGetAllPaymentMethods() {
		paymentMethodRepository.findAll().forEach((payment)->{
			System.out.println(payment.toString());
		});
		
	}
}
