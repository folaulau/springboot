package com.folaukaveinga.multidbs.member;

import java.sql.PreparedStatement;
import java.sql.Statement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Service;

@Service
public class MemberService {

	private Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public Member save(Member member) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		StringBuilder query = new StringBuilder();
		query.append("INSERT INTO member ");
		query.append("(first_name, last_name) ");
		query.append("VALUES(?, ?) ");

		log.info("query={}, firstName={}, lastName={}", query.toString(), member.getFirstName(), member.getLastName());

		jdbcTemplate.update(connection -> {
			PreparedStatement ps = connection.prepareStatement(query.toString(), Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, member.getFirstName());
			ps.setString(2, member.getLastName());
			return ps;
		}, keyHolder);

		Number key = keyHolder.getKey();

		if (key != null) {
			member.setId(key.longValue());
		}

		return member;

	}

	public Member getById(long id) {
		return jdbcTemplate.queryForObject("SELECT * FROM member WHERE id = ?", new Object[] { id },
				new MemberRowMapper());
	}
}
