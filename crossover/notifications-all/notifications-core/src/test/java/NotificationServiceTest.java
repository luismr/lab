import notifications.api.NotificationTO;
import notifications.core.NotificationCoreConfig;
import notifications.core.service.NotificationService;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = NotificationCoreConfig.class)
public class NotificationServiceTest {

	@Autowired
	private NotificationService notificationService;
	
//	@Test
	public void testCreateNotification() {
		NotificationTO notificationTO = new NotificationTO();
		notificationTO.setCategoryId(1);
		notificationTO.setInstanceId(1);
		notificationTO.setTokenId("cfcd208495d565ef66e7dff9f98764da");
		
		notificationTO.setTitle("Notification Test");
		notificationTO.setData("http://projects.spring.io/spring-data/");
		
		notificationService.send(notificationTO);
	}
	
	@Test
	public void testFlush() {
		notificationService.flushAllDone();
	}
	
}
