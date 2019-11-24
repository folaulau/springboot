package com.folaukaveinga.testing.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class UserValidatorService {
	
	private final Logger log = LoggerFactory.getLogger(this.getClass());

	public void sayHi() {
		System.out.println("saying hi from UserValidatorService!");
	}
}
