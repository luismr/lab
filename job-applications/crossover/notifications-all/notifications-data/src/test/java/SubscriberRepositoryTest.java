import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import javax.transaction.Transactional;

import notifications.data.NotificationDataConfig;
import notifications.data.domain.Subscriber;
import notifications.data.repository.SubscriberRepository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = NotificationDataConfig.class)
public class SubscriberRepositoryTest {

	@Autowired 
	SubscriberRepository subscriberRepository;
	
	@Test
	@Transactional
	public void testFindAll() {
		Iterable<Subscriber> subscribers = subscriberRepository.findAll();
		assertNotNull(subscribers);
		
		for (Subscriber subscriber : subscribers) {
			System.out.println(subscriber);
		}
		
	}
	
	@Test
	public void testCount() {
		long count = subscriberRepository.count();
		assertTrue(count > 0);
		
		System.out.println(count);
	}

}
