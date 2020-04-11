package com.folaukaveinga.multidbs.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
public class DatabaseConfig {

	private Logger log = LoggerFactory.getLogger(this.getClass());

	@Bean
	public HikariDataSource dataSource() {
		log.info("Configuring datasource...");

		// Local
		String username = "sa";
		String pass = "";

		// embeded h2
		HikariConfig config = new HikariConfig();
		config.setJdbcUrl("jdbc:h2:mem:testdb");
		//config.setDriverClassName("org.h2.Driver");
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
}
