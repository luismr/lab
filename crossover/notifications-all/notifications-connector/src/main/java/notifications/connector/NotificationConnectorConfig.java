/**
 * 
 */
package notifications.connector;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.context.support.ResourceBundleMessageSource;
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
@ComponentScan(basePackages = {"notifications.connector.service", "notifications.connector.adaptor"})
@PropertySource("classpath:mailer.properties")
@EnableTransactionManagement
public class NotificationConnectorConfig {

	private static final String MESSAGE_SOURCE_BASE_NAME = "i18n/notification-connector-message";

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
    
}
