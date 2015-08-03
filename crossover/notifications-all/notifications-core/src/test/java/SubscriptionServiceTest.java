import static org.junit.Assert.assertTrue;
import notifications.core.NotificationCoreConfig;
import notifications.core.service.SubscriptionService;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = NotificationCoreConfig.class)
public class SubscriptionServiceTest {

	@Autowired
	private SubscriptionService subscriptionService;

	@Test
	public void testDummy() {
		assertTrue(true);
	}
	
//	@Test
	public void testLoadUnsynchronyzedNotifications() {
		subscriptionService.loadUnsychronizedNotifications();
	}
	
//	@Test
	public void selectNewEntriesToProcessTest() {
		subscriptionService.selectNewEntriesToProcess();
	}
	
	/**
	 * Execute immediate push notification messages
	 */
//	@Test
	public void pushTest() {
		subscriptionService.push();
	}
	
	/**
	 * It will wait 10 minutes to monitor log files to see if thread worker
	 * is logging and working fine
	 * 
	 * please enable it when it is extremely necessary
	 * @throws InterruptedException 
	 */
//	@Test
	public void scheduleTest() throws InterruptedException {
		Thread sleeper = new Thread(new Runnable() {
			
			@Override
			public void run() {
				for (int i = 0; i < 20; i++) {
					try {
						Thread.sleep(60000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		});
		sleeper.run();
	}
	
}
