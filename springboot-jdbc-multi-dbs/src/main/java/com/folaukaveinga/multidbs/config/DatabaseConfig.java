package com.folaukaveinga.multidbs.config;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import com.folaukaveinga.multidbs.database.ClientRoutingSource;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
public class DatabaseConfig {

	private Logger log = LoggerFactory.getLogger(this.getClass());

	@Bean(name = "adminDataSource")
	public HikariDataSource adminDataSource() {
		log.info("Configuring datasource...");

		// Local
		String username = "root";
		String pass = "";
		String adminDb = "springboot_admin";
		String url = "jdbc:mysql://localhost:3306/" + adminDb
				+ "?createDatabaseIfNotExist=true&useUnicode=true&characterEncoding=utf8&useSSL=false&serverTimezone=UTC";

		HikariConfig config = new HikariConfig();
		config.setJdbcUrl(url);
		config.setUsername(username);
		config.setPassword(pass);
		config.addDataSourceProperty("cachePrepStmts", "true");
		config.addDataSourceProperty("prepStmtCacheSize", "250");
		config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");

		HikariDataSource hds = new HikariDataSource(config);
		hds.setMaximumPoolSize(10);
		hds.setMinimumIdle(5);
		hds.setMaxLifetime(30);

		return hds;
	}

	@Bean(name = "adminJdbcTemplate")
	public JdbcTemplate adminJdbcTemplate(@Qualifier("adminDataSource") DataSource adminDataSource) {
		return new JdbcTemplate(adminDataSource);
	}

	@Bean(name = "clientDataSource")
	public DataSource clientDataSource() {
		ClientRoutingSource dataSource = new ClientRoutingSource();
		dataSource.setTargetDataSources(dataSource.createTargetDataSources());
		return dataSource;
	}

	@Bean(name = "clientJdbcTemplate")
	public JdbcTemplate clientJdbcTemplate(@Qualifier("clientDataSource") DataSource clientDataSource) {
		return new JdbcTemplate(clientDataSource);
	}
}
