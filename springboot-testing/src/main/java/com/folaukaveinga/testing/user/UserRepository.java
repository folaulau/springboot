package com.folaukaveinga.testing.user;


import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.scheduling.annotation.Async;
import org.springframework.util.concurrent.ListenableFuture;

import java.lang.String;
import java.util.List;

public interface UserRepository extends JpaRepository<User, Long>{

	@Async
	public Future<List<User>> findByAge(int age);
	
	@Async
	public CompletableFuture<List<User>> findByName(String name);
	
	@Async
	public ListenableFuture<User> findByEmail(String email);
}
