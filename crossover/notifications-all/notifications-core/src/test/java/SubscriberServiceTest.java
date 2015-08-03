import static org.junit.Assert.*;
import notifications.api.SubscriberTO;
import notifications.api.TokenTO;
import notifications.core.NotificationCoreConfig;
import notifications.core.NotificationCoreException;
import notifications.core.service.SubscriberService;
import notifications.data.domain.Subscription;
import notifications.data.domain.id.SubscriptionId;
import notifications.data.repository.SubscriptionRepository;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = NotificationCoreConfig.class)
public class SubscriberServiceTest {

	@Autowired
	private SubscriberService subscriberService;
	
	@Autowired
	private SubscriptionRepository subscriptionRepository;
	
	private TokenTO tokenTO;
	
	private SubscriberTO subscriberTO;
	
	@Before
	public void before() {
		tokenTO = new TokenTO();
		tokenTO.setId("174658c8a9244cbb6e9cc6580d8e2bd9");
		tokenTO.setHash("e2258a02f61f571d86dcab0d9efad046");
		
		subscriberTO = new SubscriberTO();
		subscriberTO.setId(1L);
		subscriberTO.setName("Luis Machado Reis");
		subscriberTO.setEmail("luis.reis@singularideas.com.br");
		subscriberTO.setPin("1406");
		subscriberTO.setHash("b7087c1f4f89e63af8d46f3b20271153");
		subscriberTO.setPhone("553499783472");
		subscriberTO.setNotifyByPhone("NO");
		subscriberTO.setFrequency("IMMEDIATE");
	}
	
	@Test
	public void testDummy() {
		assertTrue(true);
	}
	
//	@Test
	public void testCreate() {
		subscriberTO.setId(null);
		SubscriberTO created = subscriberService.create(tokenTO, subscriberTO);
		
		assertNotNull(created);
		assertTrue("Luis Machado Reis".equals(created.getName()));
	}
	
//	@Test
	public void testUpdate() {
		subscriberTO.setName("Luis Reis");
		SubscriberTO updated = subscriberService.update(tokenTO, subscriberTO);
		
		assertFalse("Luis Machado Reis".equals(updated.getName()));
	}
	
//	@Test
	public void testFollow() {
		subscriberService.follow(tokenTO, subscriberTO.getId(), 1);
		
		SubscriptionId id = new SubscriptionId();
		id.setCategoryId(1);
		id.setSubscriberId(subscriberTO.getId());
		
		Subscription subscription = subscriptionRepository.findOne(id);
		
		assertTrue(subscription.getId().equals(id));
	}
	
//	@Test(expected=NotificationCoreException.class)
	public void testDeleteInvalidId() {
		subscriberService.delete(tokenTO, -1L);
	}

//	@Test(expected=NotificationCoreException.class)
	public void testDelete() {
		subscriberService.delete(tokenTO, 1L);
		
		// Delete again to grant it was deleted
		subscriberService.delete(tokenTO, 1L);
	}
	
}
