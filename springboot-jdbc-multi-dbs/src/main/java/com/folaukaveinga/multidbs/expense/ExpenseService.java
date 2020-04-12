package com.folaukaveinga.multidbs.expense;

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
public class ExpenseService {

	private Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	@Qualifier("clientJdbcTemplate")
	private JdbcTemplate clientJdbcTemplate;

	public Expense save(Expense expense) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		StringBuilder query = new StringBuilder();
		query.append("INSERT INTO expense ");
		query.append("(member_id, total_amount, total_amount_paid, merchant_name) ");
		query.append("VALUES(?, ?, ?, ?) ");

		log.info("query={}", query.toString());

		clientJdbcTemplate.update(connection -> {
			PreparedStatement ps = connection.prepareStatement(query.toString(), Statement.RETURN_GENERATED_KEYS);
			ps.setLong(1, expense.getMemberId());
			ps.setDouble(2, expense.getTotalAmount());
			ps.setDouble(3, expense.getTotalAmountPaid());
			ps.setString(4, expense.getMerchantName());

			return ps;
		}, keyHolder);

		Number key = keyHolder.getKey();

		if (key != null) {
			expense.setId(key.longValue());
		}

		return expense;

	}
}
