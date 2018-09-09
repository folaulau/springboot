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
	private AccountRepository userRepository;
	
	@Override
	public Account create(Account user) {
		log.info("create(..)");
		return userRepository.saveAndFlush(user);
	}

	@Override
	public Account update(Account user) {
		log.info("update(..)");
		return userRepository.saveAndFlush(user);
	}

	@Override
	public Account getById(Long id) {
		log.info("getById(..)");
		return userRepository.getOne(id);
	}

	@Override
	public void remove(Long id) {
		log.info("remove(..)");
		userRepository.deleteById(id);
	}

	@Override
	public List<Account> getAll() {
		log.info("getAll()");
		return userRepository.findAll();
	}

}
