package com.folaukaveinga.springbootexceptionhandling.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.folaukaveinga.springbootexceptionhandling.domain.User;
import com.folaukaveinga.springbootexceptionhandling.exception.ApiError;
import com.folaukaveinga.springbootexceptionhandling.exception.GetException;
import com.folaukaveinga.springbootexceptionhandling.exception.ProcessException;
import com.folaukaveinga.springbootexceptionhandling.repository.UserRepository;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;
	
	public User save(User user) {
		User createdUser = null;
		try {
			createdUser = this.userRepository.save(user);
			int x = 3/0;
		} catch (DataIntegrityViolationException e) {
			throw new ProcessException(new ApiError(HttpStatus.CONFLICT, "Email, "+user.getEmail()+", is taken. "));
		} catch (Throwable e) {
			throw new ProcessException(new ApiError(HttpStatus.CONFLICT, "User could not be saved."));
		}
		
		return createdUser;
	}
	
	@Transactional(rollbackOn={ProcessException.class})
	public List<User> saveUsers(List<User> users) {
		List<User> createdUsers = null;
		try {
			createdUsers = this.userRepository.saveAll(users);
			int x = 3/0;
		} catch (DataIntegrityViolationException e) {
			String erroMsg = getErrorMsg(e.getMostSpecificCause().getMessage());
			System.out.println("DataIntegrityViolationException - "+erroMsg);
			throw new ProcessException(new ApiError(HttpStatus.CONFLICT, "Users could not be saved. - "+erroMsg));
		} catch (RuntimeException e) {
			//userRepository.deleteInBatch(createdUsers);
			throw new ProcessException(new ApiError(HttpStatus.CONFLICT, "Users could not be saved. - "+e.getMessage()));
		}
		return createdUsers;
	}
	
	public User getUserById(int id) throws RuntimeException {
		Optional<User> optionalUser = this.userRepository.findById(id);
		return optionalUser.orElseThrow(() -> new GetException(new ApiError(HttpStatus.NOT_FOUND, "Entity not found with this id, "+id)));
	}
	
	
	private String getErrorMsg(String rawMsg) {
		StringBuilder errorMsg = new StringBuilder();
		if(rawMsg.contains("Duplicate entry")) {
			String field = rawMsg.substring(rawMsg.indexOf("'")+1, rawMsg.indexOf("'", 17));
			errorMsg.append(field);
			errorMsg.append(" is taken.");
		}
		return errorMsg.toString();
	}
	
	
}
