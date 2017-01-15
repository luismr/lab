package br.com.singularideas.labs.knowhub.model;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@ComponentScan({ "br.com.singularideas.labs.knowhub.model" })
@EnableTransactionManagement
public class ModelConfig {

	private static final String PROPERTY_NAME_DATABASE_DRIVER = "db.driver";
    private static final String PROPERTY_NAME_DATABASE_PASSWORD = "db.password";
    private static final String PROPERTY_NAME_DATABASE_URL = "db.url";
    private static final String PROPERTY_NAME_DATABASE_USERNAME = "db.username";
  
	@Resource
	private Environment env;

	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource datasource = new DriverManagerDataSource();

		datasource.setDriverClassName(env.getProperty(PROPERTY_NAME_DATABASE_DRIVER, "com.mysql.jdbc.Driver"));
		datasource.setUrl(env.getProperty(PROPERTY_NAME_DATABASE_URL, "jdbc:mysql://localhost:3306/knowhub"));
		datasource.setUsername(env.getProperty(PROPERTY_NAME_DATABASE_USERNAME, "knowhub"));
		datasource.setPassword(env.getProperty(PROPERTY_NAME_DATABASE_PASSWORD, "knowhub-passwd"));

		return datasource;
	}
	
	@Bean
	@Autowired
	@Scope("prototype")
	public JdbcTemplate jdbcTemplate(DataSource dataSource) {
		JdbcTemplate template = new JdbcTemplate(dataSource);
		return template;
	}

}
