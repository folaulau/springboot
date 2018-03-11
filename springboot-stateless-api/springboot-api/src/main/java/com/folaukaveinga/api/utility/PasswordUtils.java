/**
 * 
 */
package com.folaukaveinga.api.utility;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.management.RuntimeErrorException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
/**
 * @author fkaveinga
 *
 */
public final class PasswordUtils {
	private final static Logger log = LoggerFactory.getLogger(PasswordUtils.class.getName());

	final static PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	
	private PasswordUtils() {
	}

	/*
	 * Password for development only
	 */
	public static String hashPassword(final String password) {
		if (password == null || password.length() < 1)
			throw new RuntimeErrorException(null, "Password must not be null");
		else
			return passwordEncoder.encode(password);
	}
	
	public static boolean verify(String password, String hashPassword) {
		return passwordEncoder.matches(password, hashPassword);
	}
	
}
