/**
 * 
 */
package notifications.war;

import notifications.core.NotificationCoreConfig;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * Application Config for Spring
 * 
 * Mapping Beans
 * 
 * @author luismr
 *
 */
@Configuration
@ComponentScan("notifications.war.controller")
@Import(NotificationCoreConfig.class)
@EnableTransactionManagement
@EnableWebMvc
@PropertySource("classpath:super-token.properties")
public class NotificationWarConfig {

}
