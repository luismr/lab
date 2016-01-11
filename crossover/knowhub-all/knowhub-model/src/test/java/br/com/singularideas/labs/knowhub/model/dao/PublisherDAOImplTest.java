package br.com.singularideas.labs.knowhub.model.dao;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import br.com.singularideas.labs.knowhub.common.data.Publisher;
import br.com.singularideas.labs.knowhub.model.ModelConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { ModelConfig.class })
public class PublisherDAOImplTest {

	@Autowired
	private PublisherDAO publisherDAO;

	@Test
	public void testSave() {
		Publisher p = new Publisher();
		p.setName("Luis Machado Reis");
		p.setEmail("luis.reis@singularideas.com.br");
		p.setPassword("teste123");
		p.setContact("contact123");

		Publisher inserted = publisherDAO.save(p);
		assertNotNull(inserted);
		assertNotNull(inserted.getId());

		publisherDAO.deleteById(inserted.getId());
	}

	@Test
	public void testGetById() {
		Publisher p = new Publisher();
		p.setName("Luis Machado Reis");
		p.setEmail("luis.reis@singularideas.com.br");
		p.setPassword("teste123");
		p.setContact("contact123");

		Publisher inserted = publisherDAO.save(p);
		assertNotNull(inserted);
		assertNotNull(inserted.getId());

		Publisher publisher = publisherDAO.getById(inserted.getId());
		assertNotNull(publisher);
		assertNotNull(publisher.getName());
		assertTrue(publisher.getName().contains("Luis"));

		publisherDAO.deleteById(inserted.getId());
	}

	@Test
	public void testUpdate() {
		Publisher p = new Publisher();
		p.setName("Luis Machado Reis");
		p.setEmail("luis.reis@singularideas.com.br");
		p.setPassword("teste123");
		p.setContact("contact123");

		Publisher inserted = publisherDAO.save(p);
		assertNotNull(inserted);
		assertNotNull(inserted.getId());

		Publisher publisher = publisherDAO.getById(inserted.getId());

		String oldEmail = publisher.getEmail();

		String newEmail = "luismr@singularideas.com.br";
		publisher.setEmail(newEmail);

		Publisher updated = publisherDAO.update(publisher);
		assertNotNull(updated);
		assertTrue(newEmail.equals(updated.getEmail()));

		publisher.setEmail(oldEmail);
		updated = publisherDAO.update(publisher);
		assertNotNull(updated);
		assertTrue(publisher.equals(updated));

		publisherDAO.deleteById(inserted.getId());
	}

	@Test
	public void testGetAll() {
		Publisher p = new Publisher();
		p.setName("Luis Machado Reis");
		p.setEmail("luis.reis@singularideas.com.br");
		p.setPassword("teste123");
		p.setContact("contact123");

		Publisher inserted = publisherDAO.save(p);
		assertNotNull(inserted);
		assertNotNull(inserted.getId());

		List<Publisher> publishers = publisherDAO.getAll();
		assertNotNull(publishers);
		assertTrue(publishers.size() > 0);
		
		publisherDAO.deleteById(inserted.getId());
	}

	@Test
	public void testDeleteById() {
		Publisher p = new Publisher();
		p.setName("Luis Machado Reis");
		p.setEmail("luis.reis@singularideas.com.br");
		p.setPassword("teste123");
		p.setContact("contact123");

		Publisher inserted = publisherDAO.save(p);
		assertNotNull(inserted);
		assertNotNull(inserted.getId());
		
		publisherDAO.deleteById(inserted.getId());
	}

	@Test
	public void testGetByEmail() {
		Publisher p = new Publisher();
		p.setName("Luis Machado Reis");
		p.setEmail("luis.reis@singularideas.com.br");
		p.setPassword("teste123");
		p.setContact("contact123");
		
		Publisher inserted = publisherDAO.save(p);
		assertNotNull(inserted);
		assertNotNull(inserted.getId());
		
		Publisher query = publisherDAO.getByEmail(inserted.getEmail());
		assertNotNull(query);
		
		assertTrue(inserted.getEmail().equals(query.getEmail()));
		
		publisherDAO.deleteById(inserted.getId());
	}
	
}
