package com.folaukaveinga.testing.user;

import java.util.concurrent.CompletableFuture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.scheduling.annotation.Async;
import java.lang.String;
import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

	@Async
	public CompletableFuture<List<User>> findByLastName(String lastName);

	 User findByEmail(String email);
}
