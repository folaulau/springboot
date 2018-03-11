package com.folaukaveinga.statelessapi;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.folaukaveinga.api.security.JwtTokenUtil;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Clock;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.DefaultClock;

//@RunWith(SpringRunner.class)
//@SpringBootTest
public class JwtTester {
	
	//@Autowired
	private JwtTokenUtil twtTokenUtil = new JwtTokenUtil();
	
	@Test
	public void test() {
//		String username = "folaukaveinga@gmail.com";
//		String jwtToken = twtTokenUtil.generateToken(username);
//		
//		
//		String confirmedUsername = twtTokenUtil.getUsernameFromToken(jwtToken);
//		
//		System.out.println("username: "+username);
//		System.out.println("confirmedUsername: "+confirmedUsername);
//		System.out.println("token: "+jwtToken);
//		
//		try {
//			Thread.sleep(1000);
//		} catch (Exception e) {
//			// TODO: handle exception
//		}
//		
//		boolean result = twtTokenUtil.isTokenExpired(jwtToken);
//		
//		System.out.println("result: "+result);
//		
//		try {
//			Thread.sleep(1000);
//		} catch (Exception e) {
//			// TODO: handle exception
//		}
//		
//		result = twtTokenUtil.isTokenExpired(jwtToken);
//		
//		System.out.println("result: "+result);
		
	}

}
