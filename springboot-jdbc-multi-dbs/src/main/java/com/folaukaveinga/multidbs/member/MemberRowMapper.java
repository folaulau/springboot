package com.folaukaveinga.multidbs.member;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class MemberRowMapper implements RowMapper<Member> {

	@Override
	public Member mapRow(ResultSet rs, int rowNum) throws SQLException {
		Member member = new Member();
		member.setId(rs.getLong("id"));
		member.setFirstName(rs.getString("first_name"));
		member.setLastName(rs.getString("last_name"));
		return member;
	}

}
