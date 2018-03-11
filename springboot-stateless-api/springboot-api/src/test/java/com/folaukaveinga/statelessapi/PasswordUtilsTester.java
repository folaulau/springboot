package com.folaukaveinga.statelessapi;

import static org.junit.Assert.*;

import org.junit.Test;

import com.folaukaveinga.api.utility.PasswordUtils;

public class PasswordUtilsTester {

	@Test
	public void test() {
		
		String userHashedPassword = PasswordUtils.hashPassword("password");
		System.out.println("userHashedPassword: "+userHashedPassword);
		
		String adminHashedPassword = PasswordUtils.hashPassword("admin");
		System.out.println("adminHashedPassword: "+adminHashedPassword);
	}

}
