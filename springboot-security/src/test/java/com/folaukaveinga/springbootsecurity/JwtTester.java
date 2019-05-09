package com.folaukaveinga.springbootsecurity;

import static org.junit.Assert.*;

import java.util.Date;

import org.apache.commons.lang3.time.DateUtils;
import org.junit.Test;

import com.folaukaveinga.jwt.JwtPayload;
import com.folaukaveinga.jwt.JwtTokenUtils;
import com.folaukaveinga.role.Role;
import com.folaukaveinga.user.User;
import com.folaukaveinga.utils.ObjectUtils;
import com.folaukaveinga.utils.RandomGeneratorUtils;

public class JwtTester {

	@Test
	public void generateJwtToken() {
		User user = new User();
		user.setEmail("folaukaveinga@gmail.com");
		user.setName("Folau");
		user.setUid(RandomGeneratorUtils.getUuid());
		
		Role role = new Role();
		role.setAuthority(Role.ADMIN);
		role.addUser(user);
		user.addRole(role);
		
		role = new Role();
		role.setAuthority(Role.USER);
		role.addUser(user);
		user.addRole(role);
		
		String jti = RandomGeneratorUtils.getAlphaNumeric(20);
		JwtPayload payload = new JwtPayload(user, jti);
//		payload.setNbf(DateUtils.addDays(new Date(), 5));
//		payload.setIat(DateUtils.addDays(new Date(), 3));
		String jwtToken = JwtTokenUtils.generateToken(payload);
		
		System.out.println("jwtToken: "+jwtToken);
	}
	
	@Test
	public void getPayloadFromJwtToken() {
		String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1aWQiOiJyYW1PbHdjWXJwWmVtbmVVWmVoSyIsInJvbGVzIjpbIlVTRVIiLCJBRE1JTiJdLCJuYW1lIjoiRm9sYXUiLCJpc3MiOiJrYXZlaW5nYS1zZWN1cml0eS1hcGkiLCJpYXQiOjE1NTcwMTgwMzAsImVtYWlsIjoiZm9sYXVrYXZlaW5nYUBnbWFpbC5jb20iLCJqdGkiOiIyTjJvNUF0OWlFOXRWenZqeHlTZiJ9.73DUmPslKQPuH6oBLggXn6JEzYpOjmI54LuU4pVlA3A";
		JwtPayload payload = JwtTokenUtils.getJetPayload(token);
		System.out.println("payload: "+ObjectUtils.toJson(payload));
	}

}
