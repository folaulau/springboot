package com.folaukaveinga.springbootexceptionhandling.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.folaukaveinga.springbootexceptionhandling.domain.User;

public interface UserRepository extends JpaRepository<User, Integer> {

}
