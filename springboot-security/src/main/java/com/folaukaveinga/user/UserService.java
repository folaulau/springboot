package com.folaukaveinga.user;

import java.util.Optional;

import javax.validation.Valid;

import com.folaukaveinga.dto.SessionDTO;
import com.folaukaveinga.dto.SignupRequest;

public interface UserService {
	
	User create(User user);
	
	User getById(Long id);
	
	User getProfileById(Long id);
	
	User getByUid(String uid);
	
	Optional<User> findByUid(String uid);

	Optional<User> findByEmail(String email);
	
	User getByEmail(String email);

	SessionDTO signUp(SignupRequest signupRequest);
}
