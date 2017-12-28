package com.folaukaveinga.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.folaukaveinga.springboot.domain.User;

public interface UserRepository extends JpaRepository<User, Long>{

}
