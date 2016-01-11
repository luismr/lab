package br.com.singularideas.labs.knowhub.model.dao;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import br.com.singularideas.labs.knowhub.common.data.Channel;
import br.com.singularideas.labs.knowhub.common.data.Item;
import br.com.singularideas.labs.knowhub.common.data.Publisher;
import br.com.singularideas.labs.knowhub.common.data.Subscriber;
import br.com.singularideas.labs.knowhub.common.data.Subscription;
import br.com.singularideas.labs.knowhub.common.data.SubscriptionStatus;
import br.com.singularideas.labs.knowhub.model.ModelConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { ModelConfig.class })
public class SubscriptionDAOImplTest {

	@Autowired
	private SubscriberDAO subscriberDAO;
	
	@Autowired
	private SubscriptionDAO subscriptionDAO;
	
	@Autowired
	private ChannelDAO channelDAO;
	
	@Autowired
	private ItemDAO itemDAO;
	
	@Autowired
	private PublisherDAO publisherDAO;

	@Test
	public void testSave() {
		Publisher publisher = new Publisher();
		publisher.setName("Luis Machado Reis");
		publisher.setEmail("luis.reis@singularideas.com.br");
		publisher.setPassword("teste123");
		publisher.setContact("contact123");

		publisher = publisherDAO.save(publisher);
		assertNotNull(publisher);
		assertNotNull(publisher.getId());

		Channel channel = new Channel();
		channel.setPublisher(publisher);
		channel.setName("my 1st Channel");
		channel.setDescription("my 1st big description");
		channel.setPrice(5.50F);
		
		channel = channelDAO.save(channel);
		assertNotNull(channel);
		assertNotNull(channel.getId());
		
		Item item = new Item();
		item.setChannel(channel);
		item.setTitle("my 1st Item");
		item.setDescription("my 1st Big Description");
		item.setAuthor("Luis Machado Reis");
		item.setFilename("filename.1st");
		
		item = itemDAO.save(item);
		assertNotNull(item);
		assertNotNull(item.getId());
		
		Subscriber subscriber = new Subscriber();
		subscriber.setName("Luis Machado Reis");
		subscriber.setEmail("luis.reis@singularideas.com.br");
		subscriber.setPassword("teste123");
		subscriber.setKey("key123");

		subscriber = subscriberDAO.save(subscriber);
		assertNotNull(subscriber);
		assertNotNull(subscriber.getId());

		Subscription subscription = new Subscription();
		subscription.setChannel(channel);
		subscription.setSubscriber(subscriber);
		subscription.setExpires(new Date());
		subscription.setStatus(SubscriptionStatus.ACTIVE);
		
		subscription = subscriptionDAO.save(subscription);
		assertNotNull(subscription);
		assertNotNull(subscription.getId());
		
		subscriptionDAO.deleteById(subscription.getId());
		subscriberDAO.deleteById(subscriber.getId());

		itemDAO.deleteById(item.getId());
		channelDAO.deleteById(channel.getId());
		publisherDAO.deleteById(publisher.getId());
	}

	@Test
	public void testGetById() {
		Publisher publisher = new Publisher();
		publisher.setName("Luis Machado Reis");
		publisher.setEmail("luis.reis@singularideas.com.br");
		publisher.setPassword("teste123");
		publisher.setContact("contact123");

		publisher = publisherDAO.save(publisher);
		assertNotNull(publisher);
		assertNotNull(publisher.getId());

		Channel channel = new Channel();
		channel.setPublisher(publisher);
		channel.setName("my 1st Channel");
		channel.setDescription("my 1st big description");
		channel.setPrice(5.50F);
		
		channel = channelDAO.save(channel);
		assertNotNull(channel);
		assertNotNull(channel.getId());
		
		Item item = new Item();
		item.setChannel(channel);
		item.setTitle("my 1st Item");
		item.setDescription("my 1st Big Description");
		item.setAuthor("Luis Machado Reis");
		item.setFilename("filename.1st");
		
		item = itemDAO.save(item);
		assertNotNull(item);
		assertNotNull(item.getId());
		
		Subscriber subscriber = new Subscriber();
		subscriber.setName("Luis Machado Reis");
		subscriber.setEmail("luis.reis@singularideas.com.br");
		subscriber.setPassword("teste123");
		subscriber.setKey("key123");

		subscriber = subscriberDAO.save(subscriber);
		assertNotNull(subscriber);
		assertNotNull(subscriber.getId());

		Subscription subscription = new Subscription();
		subscription.setChannel(channel);
		subscription.setSubscriber(subscriber);
		subscription.setExpires(new Date());
		subscription.setStatus(SubscriptionStatus.ACTIVE);
		
		subscription = subscriptionDAO.save(subscription);
		assertNotNull(subscription);
		assertNotNull(subscription.getId());

		subscription = subscriptionDAO.getById(subscription.getId());
		assertNotNull(subscription);
		assertNotNull(subscription.getId());
		
		subscriptionDAO.deleteById(subscription.getId());
		subscriberDAO.deleteById(subscriber.getId());

		itemDAO.deleteById(item.getId());
		channelDAO.deleteById(channel.getId());
		publisherDAO.deleteById(publisher.getId());
	}

	@Test
	public void testUpdate() {
		Publisher publisher = new Publisher();
		publisher.setName("Luis Machado Reis");
		publisher.setEmail("luis.reis@singularideas.com.br");
		publisher.setPassword("teste123");
		publisher.setContact("contact123");

		publisher = publisherDAO.save(publisher);
		assertNotNull(publisher);
		assertNotNull(publisher.getId());

		Channel channel = new Channel();
		channel.setPublisher(publisher);
		channel.setName("my 1st Channel");
		channel.setDescription("my 1st big description");
		channel.setPrice(5.50F);
		
		channel = channelDAO.save(channel);
		assertNotNull(channel);
		assertNotNull(channel.getId());
		
		Item item = new Item();
		item.setChannel(channel);
		item.setTitle("my 1st Item");
		item.setDescription("my 1st Big Description");
		item.setAuthor("Luis Machado Reis");
		item.setFilename("filename.1st");
		
		item = itemDAO.save(item);
		assertNotNull(item);
		assertNotNull(item.getId());
		
		Subscriber subscriber = new Subscriber();
		subscriber.setName("Luis Machado Reis");
		subscriber.setEmail("luis.reis@singularideas.com.br");
		subscriber.setPassword("teste123");
		subscriber.setKey("key123");

		subscriber = subscriberDAO.save(subscriber);
		assertNotNull(subscriber);
		assertNotNull(subscriber.getId());

		Subscription subscription = new Subscription();
		subscription.setChannel(channel);
		subscription.setSubscriber(subscriber);
		subscription.setExpires(new Date());
		subscription.setStatus(SubscriptionStatus.ACTIVE);
		
		subscription = subscriptionDAO.save(subscription);
		assertNotNull(subscription);
		assertNotNull(subscription.getId());

		subscription.setStatus(SubscriptionStatus.CANCELED);
		subscription = subscriptionDAO.save(subscription);
		assertNotNull(subscription);
		assertNotNull(subscription.getId());
		assertTrue(SubscriptionStatus.CANCELED.equals(subscription.getStatus()));
		
		for (Subscription s : subscriptionDAO.getBySubscriber(subscriber)) {
			subscriptionDAO.deleteById(s.getId());
		}
		
		subscriberDAO.deleteById(subscriber.getId());

		itemDAO.deleteById(item.getId());
		channelDAO.deleteById(channel.getId());
		publisherDAO.deleteById(publisher.getId());
	}

	@Test
	public void testGetAll() {
		Publisher publisher = new Publisher();
		publisher.setName("Luis Machado Reis");
		publisher.setEmail("luis.reis@singularideas.com.br");
		publisher.setPassword("teste123");
		publisher.setContact("contact123");

		publisher = publisherDAO.save(publisher);
		assertNotNull(publisher);
		assertNotNull(publisher.getId());

		Channel channel = new Channel();
		channel.setPublisher(publisher);
		channel.setName("my 1st Channel");
		channel.setDescription("my 1st big description");
		channel.setPrice(5.50F);
		
		channel = channelDAO.save(channel);
		assertNotNull(channel);
		assertNotNull(channel.getId());
		
		Item item = new Item();
		item.setChannel(channel);
		item.setTitle("my 1st Item");
		item.setDescription("my 1st Big Description");
		item.setAuthor("Luis Machado Reis");
		item.setFilename("filename.1st");
		
		item = itemDAO.save(item);
		assertNotNull(item);
		assertNotNull(item.getId());
		
		Subscriber subscriber = new Subscriber();
		subscriber.setName("Luis Machado Reis");
		subscriber.setEmail("luis.reis@singularideas.com.br");
		subscriber.setPassword("teste123");
		subscriber.setKey("key123");

		subscriber = subscriberDAO.save(subscriber);
		assertNotNull(subscriber);
		assertNotNull(subscriber.getId());

		Subscription subscription = new Subscription();
		subscription.setChannel(channel);
		subscription.setSubscriber(subscriber);
		subscription.setExpires(new Date());
		subscription.setStatus(SubscriptionStatus.ACTIVE);
		
		subscription = subscriptionDAO.save(subscription);
		assertNotNull(subscription);
		assertNotNull(subscription.getId());
		
		subscription = new Subscription();
		subscription.setChannel(channel);
		subscription.setSubscriber(subscriber);
		subscription.setExpires(new Date());
		subscription.setStatus(SubscriptionStatus.SUSPENDED);
		
		subscription = subscriptionDAO.save(subscription);
		assertNotNull(subscription);
		assertNotNull(subscription.getId());
		
		List<Subscription> subscriptions = subscriptionDAO.getAll();
		assertNotNull(subscriptions);
		assertTrue(subscriptions.size() > 1);
		
		for (Subscription s : subscriptions) {
			subscriptionDAO.deleteById(s.getId());
		}
		
		subscriberDAO.deleteById(subscriber.getId());

		itemDAO.deleteById(item.getId());
		channelDAO.deleteById(channel.getId());
		publisherDAO.deleteById(publisher.getId());
	}

	@Test
	public void testGetBySubscriber() {
		Publisher publisher = new Publisher();
		publisher.setName("Luis Machado Reis");
		publisher.setEmail("luis.reis@singularideas.com.br");
		publisher.setPassword("teste123");
		publisher.setContact("contact123");

		publisher = publisherDAO.save(publisher);
		assertNotNull(publisher);
		assertNotNull(publisher.getId());

		Channel channel = new Channel();
		channel.setPublisher(publisher);
		channel.setName("my 1st Channel");
		channel.setDescription("my 1st big description");
		channel.setPrice(5.50F);
		
		channel = channelDAO.save(channel);
		assertNotNull(channel);
		assertNotNull(channel.getId());
		
		Item item = new Item();
		item.setChannel(channel);
		item.setTitle("my 1st Item");
		item.setDescription("my 1st Big Description");
		item.setAuthor("Luis Machado Reis");
		item.setFilename("filename.1st");
		
		item = itemDAO.save(item);
		assertNotNull(item);
		assertNotNull(item.getId());
		
		Subscriber subscriber = new Subscriber();
		subscriber.setName("Luis Machado Reis");
		subscriber.setEmail("luis.reis@singularideas.com.br");
		subscriber.setPassword("teste123");
		subscriber.setKey("key123");

		subscriber = subscriberDAO.save(subscriber);
		assertNotNull(subscriber);
		assertNotNull(subscriber.getId());

		Subscription subscription = new Subscription();
		subscription.setChannel(channel);
		subscription.setSubscriber(subscriber);
		subscription.setExpires(new Date());
		subscription.setStatus(SubscriptionStatus.ACTIVE);
		
		subscription = subscriptionDAO.save(subscription);
		assertNotNull(subscription);
		assertNotNull(subscription.getId());
		
		subscription = new Subscription();
		subscription.setChannel(channel);
		subscription.setSubscriber(subscriber);
		subscription.setExpires(new Date());
		subscription.setStatus(SubscriptionStatus.SUSPENDED);
		
		subscription = subscriptionDAO.save(subscription);
		assertNotNull(subscription);
		assertNotNull(subscription.getId());
		
		List<Subscription> subscriptions = subscriptionDAO.getBySubscriber(subscriber); 
		assertNotNull(subscriptions);
		assertTrue(subscriptions.size() > 1);
		
		for (Subscription s : subscriptions) {
			subscriptionDAO.deleteById(s.getId());
		}
		
		subscriberDAO.deleteById(subscriber.getId());

		itemDAO.deleteById(item.getId());
		channelDAO.deleteById(channel.getId());
		publisherDAO.deleteById(publisher.getId());
	}
	
}
