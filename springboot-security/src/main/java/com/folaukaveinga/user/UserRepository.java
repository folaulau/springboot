package com.folaukaveinga.user;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.folaukaveinga.dto.UserDto;

public interface UserRepository extends JpaRepository<User, Long> {

	Optional<User> findByUid(String uuid);
	
	User getById(Long id);
	
	User getByUid(String uid);
	
	Optional<User> findByEmail(String email);
	
	User getByEmail(String email);
}
