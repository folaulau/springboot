package com.lovemesomecoding.user;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class UserRowMapper implements RowMapper<User> {

	@Override
	public User mapRow(ResultSet rs, int rowNum) throws SQLException {
		User user = new User();
		user.setEmail(rs.getString("email"));
		user.setId(rs.getLong("id"));
		user.setUid(rs.getString("uid"));
		user.setPassword(rs.getString("password"));
		return user;
	}
	
	

}
