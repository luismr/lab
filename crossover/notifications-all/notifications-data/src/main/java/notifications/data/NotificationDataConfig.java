/**
 * 
 */
package notifications.data;

import java.util.Arrays;
import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

/**
 * Application Config for Spring
 * 
 * Mapping Beans
 * Mapping JPA
 * 
 * @author luismr
 *
 */
@Configuration
@PropertySource("classpath:application.properties")
@ComponentScan("notifications.data.service")
@EnableJpaRepositories("notifications.data.repository")
@EntityScan("notifications.data.domain")
@EnableCaching
@EnableTransactionManagement
public class NotificationDataConfig {

	private static final String MESSAGE_SOURCE_BASE_NAME = "i18n/notification-data-message";

    /**
     * Messages Source with i18n support
     * @return
     */
    @Bean
    MessageSource messageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasename(MESSAGE_SOURCE_BASE_NAME);
        messageSource.setUseCodeAsDefaultMessage(true);
        
        return messageSource;
    }

    /**
     * Property Placeholder Configurer to i18n messages
     * @return
     */
    @Bean
    static PropertySourcesPlaceholderConfigurer propertyPlaceHolderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }
    
    /**
     * Register CacheManager
     * @return
     */
    @Bean
    public CacheManager cacheManager() {
        SimpleCacheManager cacheManager = new SimpleCacheManager();
        cacheManager.setCaches(Arrays.asList(new ConcurrentMapCache("default")));
        
        return cacheManager;    
    }
    
	/**
	 * Datasource to JPA
	 * @param env Enviroment Variables/Properties
	 * @return DataSource
	 */
	@Bean(destroyMethod = "close")
	DataSource dataSource(Environment env) {
		HikariConfig config = new HikariConfig();
		config.setDriverClassName(env.getProperty("db.driver"));
		config.setJdbcUrl(env.getProperty("db.url"));
		config.setUsername(env.getProperty("db.username"));
		config.setPassword(env.getProperty("db.password"));
		config.setConnectionTestQuery(env.getProperty("db.test"));
		
		HikariDataSource ds = new HikariDataSource(config); 
		return ds;
	}

	/**
	 * Transaction Manager
	 * @param entityManagerFactory
	 * @return
	 */
	@Bean
    JpaTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory);
        
        return transactionManager;
    }	

	/**
	 * JPA Adaptor for Hibernate
	 * @return
	 */
	@Bean
	public JpaVendorAdapter jpaVendorAdapter() {
		HibernateJpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
		jpaVendorAdapter.setDatabase(Database.MYSQL);
		jpaVendorAdapter.setGenerateDdl(true);
		return jpaVendorAdapter;
	}

	/**
	 * Local Entity Manager Factory for JPA
	 * @param env
	 * @param dataSource
	 * @return
	 */
	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(Environment env, DataSource dataSource) {
		LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactoryBean.setDataSource(dataSource);
        entityManagerFactoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        entityManagerFactoryBean.setPackagesToScan("net.petrikainulainen.springdata.jpa.todo");
 
        Properties jpaProperties = new Properties();
        jpaProperties.put("hibernate.dialect", env.getRequiredProperty("hibernate.dialect"));
        jpaProperties.put("hibernate.hbm2ddl.auto", env.getRequiredProperty("hibernate.hbm2ddl.auto"));
        jpaProperties.put("hibernate.ejb.naming_strategy", env.getRequiredProperty("hibernate.ejb.naming_strategy"));
        jpaProperties.put("hibernate.show_sql", env.getRequiredProperty("hibernate.show_sql"));
        jpaProperties.put("hibernate.format_sql", env.getRequiredProperty("hibernate.format_sql"));
        jpaProperties.put("hibernate.debug", env.getProperty("hibernate.debug", "false"));
 
        entityManagerFactoryBean.setJpaProperties(jpaProperties);
 
        return entityManagerFactoryBean;
     }

}
