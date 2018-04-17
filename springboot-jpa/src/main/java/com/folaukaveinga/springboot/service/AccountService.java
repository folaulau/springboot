package com.folaukaveinga.springboot.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.folaukaveinga.springboot.domain.Account;
import com.folaukaveinga.springboot.repository.AccountRepository;

@Service
public class AccountService {

	@Autowired
	private AccountRepository accountRepository;
	
	public Account save(Account account) {
		return accountRepository.saveAndFlush(account);
	}
	
	public Account getById(UUID uuid) {
		return this.accountRepository.findById(uuid).get();
	}
	
	public List<Account> getAllAccounts(){
		return this.accountRepository.findAll();
	}
}
