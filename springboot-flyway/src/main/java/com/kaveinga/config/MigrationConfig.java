package com.kaveinga.config;

import org.flywaydb.core.Flyway;
import org.flywaydb.core.api.configuration.FluentConfiguration;
import org.springframework.boot.autoconfigure.flyway.FlywayMigrationInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Profile;

@Configuration
public class MigrationConfig {

	/**
	 * Override default flyway initializer to do nothing
	 */
	@Bean
	FlywayMigrationInitializer flywayInitializer() {
		return new FlywayMigrationInitializer(setUpFlyway(), (f) -> {
		});
	}

	/**
	 * Create a second flyway initializer to run after jpa has created the schema
	 */
	@Bean
	@DependsOn("entityManagerFactory")
	FlywayMigrationInitializer delayedFlywayInitializer() {
		Flyway flyway = setUpFlyway();
		return new FlywayMigrationInitializer(flyway, null);
	}

	private Flyway setUpFlyway() {
		String database = "spring_boot_flyway";
		String username = "root";
		String password = "";
		String url = "jdbc:mysql://localhost:3306/"+database+"?autoreconnect=true&useUnicode=true&characterEncoding=utf8&useSSL=false&serverTimezone=UTC";
		FluentConfiguration configuration = Flyway.configure().dataSource(url, username, password);
		configuration.schemas(database);
		configuration.baselineOnMigrate(true);
		return configuration.load();
	}
}
