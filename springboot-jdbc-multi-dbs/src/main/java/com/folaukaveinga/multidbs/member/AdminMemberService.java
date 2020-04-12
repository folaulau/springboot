package com.folaukaveinga.multidbs.member;

import java.sql.PreparedStatement;
import java.sql.Statement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Service;

@Service
public class AdminMemberService {

	private Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	@Qualifier("adminJdbcTemplate")
	private JdbcTemplate adminJdbcTemplate;

	public Member save(Member member) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		StringBuilder query = new StringBuilder();
		query.append("INSERT INTO member ");
		query.append("(first_name, last_name, db) ");
		query.append("VALUES(?, ?, ?) ");

		log.info("query={}", query.toString());

		adminJdbcTemplate.update(connection -> {
			PreparedStatement ps = connection.prepareStatement(query.toString(), Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, member.getFirstName());
			ps.setString(2, member.getLastName());
			ps.setString(3, member.getDb());
			
			return ps;
		}, keyHolder);

		Number key = keyHolder.getKey();

		if (key != null) {
			member.setId(key.longValue());
		}

		return member;

	}

	public Member getById(long id) {
		return adminJdbcTemplate.queryForObject("SELECT * FROM member WHERE id = ?", new Object[] { id },
				new MemberRowMapper());
	}
}
