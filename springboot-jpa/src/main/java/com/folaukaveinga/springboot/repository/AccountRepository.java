package com.folaukaveinga.springboot.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.folaukaveinga.springboot.domain.Account;

public interface AccountRepository extends JpaRepository<Account, UUID>{

}
