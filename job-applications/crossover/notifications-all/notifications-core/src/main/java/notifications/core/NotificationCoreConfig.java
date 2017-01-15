/**
 * 
 */
package notifications.core;

import java.lang.reflect.Method;
import java.util.concurrent.Executor;

import notifications.connector.NotificationConnectorConfig;
import notifications.data.NotificationDataConfig;

import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Application Config for Spring
 * 
 * Mapping Beans
 * 
 * @author luismr
 *
 */
@Configuration
@ComponentScan("notifications.core.service")
@Import({ NotificationDataConfig.class, NotificationConnectorConfig.class })
@EnableTransactionManagement
@EnableCaching
@EnableAsync
@EnableScheduling
public class NotificationCoreConfig implements AsyncConfigurer {

	private static final String MESSAGE_SOURCE_BASE_NAME = "i18n/notification-core-message";

	/**
	 * Messages Source with i18n support
	 * 
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
	 * 
	 * @return
	 */
	@Bean
	static PropertySourcesPlaceholderConfigurer propertyPlaceHolderConfigurer() {
		return new PropertySourcesPlaceholderConfigurer();
	}

	/**
	 * Task Scheduler
	 * @return
	 */
	@Bean
	TaskScheduler taskScheduler() {
		return new ThreadPoolTaskScheduler();
	}
	
	/**
	 * Async Thread Pool Executor
	 */
	@Override
	public Executor getAsyncExecutor() {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(10);
		executor.setMaxPoolSize(20);
		executor.setQueueCapacity(250);
		executor.setThreadNamePrefix("crossoverNotifications-");
		executor.initialize();

		return executor;
	}

	/**
	 * Async Exception Handling
	 */
	@Override
	public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
		return new AsyncUncaughtExceptionHandler() {

			@Override
			public void handleUncaughtException(Throwable ex, Method method, Object... params) {
				System.out.println("Exception message - " + ex.getMessage());
				
				System.out.println("Method name - " + method.getName());
				for (Object param : params) {
					System.out.println("Parameter value - " + param);
				}
			}
			
		};
	}

}
