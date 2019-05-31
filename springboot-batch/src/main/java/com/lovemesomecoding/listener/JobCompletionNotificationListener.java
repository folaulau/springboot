package com.lovemesomecoding.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.lovemesomecoding.model.User;

@Component
public class JobCompletionNotificationListener extends JobExecutionListenerSupport  {

	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public void afterJob(JobExecution jobExecution) {
		if(jobExecution.getStatus() == BatchStatus.COMPLETED) {
			log.info("!!! JOB FINISHED! Time to verify the results");

			jdbcTemplate.query("SELECT first_name, last_name FROM user",
				(rs, row) -> new User(
					rs.getString(1),
					rs.getString(2))
			).forEach(user -> log.info("Found <" + user + "> in the database."));
		}
	}
}
