package br.com.singularideas.labs.knowhub.model.dao;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import br.com.singularideas.labs.knowhub.common.data.Subscriber;
import br.com.singularideas.labs.knowhub.model.ModelConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { ModelConfig.class })
public class SubscriberDAOImplTest {

	@Autowired
	private SubscriberDAO subscriberDAO;

	@Test
	public void testSave() {
		Subscriber s = new Subscriber();
		s.setName("Luis Machado Reis");
		s.setEmail("luis.reis@singularideas.com.br");
		s.setPassword("teste123");
		s.setKey("key123");

		Subscriber inserted = subscriberDAO.save(s);
		assertNotNull(inserted);
		assertNotNull(inserted.getId());

		subscriberDAO.deleteById(inserted.getId());
	}

	@Test
	public void testGetById() {
		Subscriber s = new Subscriber();
		s.setName("Luis Machado Reis");
		s.setEmail("luis.reis@singularideas.com.br");
		s.setPassword("teste123");
		s.setKey("key123");

		Subscriber inserted = subscriberDAO.save(s);
		assertNotNull(inserted);
		assertNotNull(inserted.getId());

		Subscriber subscriber = subscriberDAO.getById(inserted.getId());
		assertNotNull(subscriber);
		assertNotNull(subscriber.getName());
		assertTrue(subscriber.getName().contains("Luis"));

		subscriberDAO.deleteById(inserted.getId());
	}

	@Test
	public void testUpdate() {
		Subscriber s = new Subscriber();
		s.setName("Luis Machado Reis");
		s.setEmail("luis.reis@singularideas.com.br");
		s.setPassword("teste123");
		s.setKey("key123");

		Subscriber inserted = subscriberDAO.save(s);
		assertNotNull(inserted);
		assertNotNull(inserted.getId());

		Subscriber subscriber = subscriberDAO.getById(inserted.getId());

		String oldEmail = subscriber.getEmail();

		String newEmail = "luismr@singularideas.com.br";
		subscriber.setEmail(newEmail);

		Subscriber updated = subscriberDAO.update(subscriber);
		assertNotNull(updated);
		assertTrue(newEmail.equals(updated.getEmail()));

		subscriber.setEmail(oldEmail);
		updated = subscriberDAO.update(subscriber);
		assertNotNull(updated);
		assertTrue(subscriber.equals(updated));

		subscriberDAO.deleteById(inserted.getId());
	}

	@Test
	public void testGetAll() {
		Subscriber s = new Subscriber();
		s.setName("Luis Machado Reis");
		s.setEmail("luis.reis@singularideas.com.br");
		s.setPassword("teste123");
		s.setKey("key123");

		Subscriber inserted = subscriberDAO.save(s);
		assertNotNull(inserted);
		assertNotNull(inserted.getId());

		List<Subscriber> subscribers = subscriberDAO.getAll();
		assertNotNull(subscribers);
		assertTrue(subscribers.size() > 0);
		
		subscriberDAO.deleteById(inserted.getId());
	}

	@Test
	public void testDeleteById() {
		Subscriber s = new Subscriber();
		s.setName("Luis Machado Reis");
		s.setEmail("luis.reis@singularideas.com.br");
		s.setPassword("teste123");
		s.setKey("key123");

		Subscriber inserted = subscriberDAO.save(s);
		assertNotNull(inserted);
		assertNotNull(inserted.getId());
		
		subscriberDAO.deleteById(inserted.getId());
	}

	@Test
	public void testGetByEmail() {
		Subscriber s = new Subscriber();
		s.setName("Luis Machado Reis");
		s.setEmail("luis.reis@singularideas.com.br");
		s.setPassword("teste123");
		s.setKey("key123");
		
		Subscriber inserted = subscriberDAO.save(s);
		assertNotNull(inserted);
		assertNotNull(inserted.getId());
		
		Subscriber query = subscriberDAO.getByEmail(inserted.getEmail());
		assertNotNull(query);
		
		assertTrue(inserted.getEmail().equals(query.getEmail()));
		
		subscriberDAO.deleteById(inserted.getId());
	}
	
}
