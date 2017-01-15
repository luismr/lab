import static org.junit.Assert.assertNotNull;
import notifications.data.NotificationDataConfig;
import notifications.data.domain.Notification;
import notifications.data.repository.NotificationRepository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = NotificationDataConfig.class)
public class NotificationRepositoryTest {

	@Autowired 
	NotificationRepository notificationRepository;
	
	@Test
	public void testFindAll() {
		Iterable<Notification> notifications = notificationRepository.findAll();
		assertNotNull(notifications);
		
		for (Notification notification: notifications) {
			System.out.println(notification);
		}
		
	}
	

}
