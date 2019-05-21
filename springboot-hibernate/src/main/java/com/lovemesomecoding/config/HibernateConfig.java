package com.lovemesomecoding.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.lovemesomecoding.model.Address;
import com.lovemesomecoding.model.Role;
import com.lovemesomecoding.model.User;
import com.zaxxer.hikari.HikariDataSource;

/**
 * Reference - https://docs.jboss.org/hibernate/orm/5.0/manual/en-US/html/ch03.html#configuration-optional <br>
 * Reference - https://www.baeldung.com/hibernate-5-spring<br>
 * Reference - https://github.com/eugenp/tutorials/blob/master/persistence-modules/spring-persistence-simple/src/main/java/com/baeldung/config/PersistenceConfig.java <br>
 * @author folaukaveinga
 *
 */

@Configuration
@EnableTransactionManagement
public class HibernateConfig {
	
	@Bean
	public LocalSessionFactoryBean sessionFactory() {
		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
		sessionFactory.setDataSource(dataSource());

		sessionFactory.setHibernateProperties(hibernateProperties());
		sessionFactory.setAnnotatedClasses(User.class, Role.class, Address.class);

		return sessionFactory;
	}
	
	private final Properties hibernateProperties() {
		final Properties hibernateProperties = new Properties();
		hibernateProperties.setProperty("hibernate.hbm2ddl.auto", "create");
		hibernateProperties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5InnoDBDialect");
		hibernateProperties.setProperty("hibernate.show_sql", "true");
		hibernateProperties.setProperty("hibernate.format_sql", "true");
		return hibernateProperties;
	}
	
	@Bean
	public DataSource dataSource() {
		Integer port = 3306;
		String host = "localhost";
		String username = "root";
		String password = "";
		String dbName = "spring_boot_hibernate";
		String url = "jdbc:mysql://" + host + ":" + port + "/" + dbName
				+ "?useUnicode=true&characterEncoding=utf8&useSSL=false&serverTimezone=UTC";
		return DataSourceBuilder.create().password(password).username(username).url(url).type(HikariDataSource.class)
				.build();
	}

	@Bean
	public PlatformTransactionManager hibernateTransactionManager() {
		HibernateTransactionManager transactionManager = new HibernateTransactionManager();
		transactionManager.setSessionFactory(sessionFactory().getObject());
		return transactionManager;
	}

	
}
