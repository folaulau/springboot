package com.folaukaveinga.multidbs.database;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import com.folaukaveinga.multidbs.security.ApiSessionUtils;

public class ClientRoutingSource extends AbstractRoutingDataSource {

	@Override
	protected String determineCurrentLookupKey() {
		return ApiSessionUtils.getCurrentUserDatabase();
	}

	public Map<Object, Object> createTargetDataSources() {
		Map<Object, Object> result = new HashMap<>();

		// key value pair of determineCurrentLookupKey() and database dataSource.

		Arrays.asList("springboot_clienta", "springboot_clientb", "test_bada").stream().forEach(database -> {
			DataSource dataSource = getDataSourceByDBName(database);
			result.put(database, dataSource);
		});

		return result;
	}

	private DataSource getDataSourceByDBName(String database) {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		String url = "jdbc:mysql://localhost:3306/" + database
				+ "?useUnicode=true&characterEncoding=utf8&useSSL=false&serverTimezone=UTC";

		dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
		dataSource.setUrl(url);
		dataSource.setUsername("root");
		dataSource.setPassword("");

		return dataSource;
	}

}
