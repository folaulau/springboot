package com.kaveinga.account;

import java.util.List;

public interface AccountService {

	Account create(Account account);
	Account update(Account account);
	Account getById(Long id);
	void remove(Long id);
	List<Account> getAll();
}
