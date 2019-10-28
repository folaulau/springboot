package com.folaukaveinga.springboot.repository;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.scheduling.annotation.Async;
import org.springframework.util.concurrent.ListenableFuture;

import com.folaukaveinga.springboot.domain.User;

import io.lettuce.core.dynamic.annotation.Param;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.lang.String;
import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

	@Async
	public Future<User> findByAge(int age);
	
	
//	Flux<User> findByLastName(String lastName);

	@Async
	public CompletableFuture<User> findByName(String name);

//	Mono<User> findByEmail(String email);
	
	
	@Procedure(name = "FIND_USER_BY_AGE")
	List<User> findUsersByAge(@Param("age") int age);
}
