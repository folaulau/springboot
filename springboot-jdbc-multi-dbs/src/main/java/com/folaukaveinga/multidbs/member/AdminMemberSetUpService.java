package com.folaukaveinga.multidbs.member;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Random;
import java.util.stream.IntStream;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

@Component
public class AdminMemberSetUpService {

	private Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	@Qualifier("adminJdbcTemplate")
	private JdbcTemplate adminJdbcTemplate;

	@Autowired
	private AdminMemberService adminMemberService;

	@PostConstruct
	public void init() {

		// create table
		boolean memberTableCreated = createMemberTable();

		if (memberTableCreated) {
			 setUpMembers();
		}

	}

	private boolean createMemberTable() {
		try {
			StringBuilder query = new StringBuilder();
			query.append("CREATE TABLE member ( ");
			query.append("id INT NOT NULL AUTO_INCREMENT, ");
			query.append("first_name VARCHAR(255) NOT NULL, ");
			query.append("last_name VARCHAR(255) NOT NULL,");
			query.append("db VARCHAR(255) NOT NULL,");
			query.append("PRIMARY KEY (`id`) ");
			query.append(") ");

			adminJdbcTemplate.execute(query.toString());

			return true;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return false;
	}

	private void setUpMembers() {
		// insert temp users

		Member member = new Member();
		member.setFirstName("Laulau");
		member.setLastName("Kaveinga");

		Random rand = new Random();
		if (rand.nextInt(10) % 2 == 0) {
			member.setDb("springboot_clienta");
		} else {
			member.setDb("springboot_clientb");
		}
		
		member = adminMemberService.save(member);
		
		log.info("new added Member={}", member.toString());
		
	}

}
