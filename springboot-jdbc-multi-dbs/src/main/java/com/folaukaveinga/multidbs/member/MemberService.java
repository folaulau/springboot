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
public class MemberService {

	private Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private AdminMemberService adminMemberService;

	public Member getById(long id) {
		return adminMemberService.getById(id);
	}
}
