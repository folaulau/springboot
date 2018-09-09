package com.kaveinga.account;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImp implements AccountService {

	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private AccountRepository accountRepository;
	
	@Override
	public Account create(Account account) {
		log.info("create(..)");
		return accountRepository.saveAndFlush(account);
	}

	@Override
	public Account update(Account account) {
		log.info("update(..)");
		return accountRepository.saveAndFlush(account);
	}

	@Override
	public Account getById(Long id) {
		log.info("getById(..)");
		return accountRepository.getOne(id);
	}

	@Override
	public void remove(Long id) {
		log.info("remove(..)");
		accountRepository.deleteById(id);
	}

	@Override
	public List<Account> getAll() {
		log.info("getAll()");
		return accountRepository.findAll();
	}

	@Override
	public void removeAll() {
		log.info("removeAll()");
		accountRepository.deleteAll();
	}

}
