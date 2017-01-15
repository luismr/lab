package br.com.singularideas.labs.knowhub.model.dao;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import br.com.singularideas.labs.knowhub.common.data.Channel;
import br.com.singularideas.labs.knowhub.common.data.Item;
import br.com.singularideas.labs.knowhub.common.data.Publisher;
import br.com.singularideas.labs.knowhub.model.ModelConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { ModelConfig.class })
public class ChannelDAOImplTest {

	@Autowired
	private PublisherDAO publisherDAO;
	
	@Autowired
	private ChannelDAO channelDAO;
	
	@Autowired
	private ItemDAO itemDAO;

	@Test
	public void testSave() {
		Publisher p = new Publisher();
		p.setName("Luis Machado Reis");
		p.setEmail("luis.reis@singularideas.com.br");
		p.setPassword("teste123");
		p.setContact("contact123");

		Publisher publisherInserted = publisherDAO.save(p);
		assertNotNull(publisherInserted);
		assertNotNull(publisherInserted.getId());

		Channel c = new Channel();
		c.setPublisher(publisherInserted);
		c.setName("my 1st Channel");
		c.setDescription("my 1st big description");
		c.setPrice(5.50F);
		
		Channel channelInserted = channelDAO.save(c);
		assertNotNull(channelInserted);
		assertNotNull(channelInserted.getId());
		
		Item i = new Item();
		i.setChannel(channelInserted);
		i.setTitle("my 1st Item");
		i.setDescription("my 1st Big Description");
		i.setAuthor("Luis Machado Reis");
		i.setFilename("filename.1st");
		
		Item itemInserted = itemDAO.save(i);
		assertNotNull(itemInserted);
		assertNotNull(itemInserted.getId());
		
		itemDAO.deleteById(itemInserted.getId());
		channelDAO.deleteById(channelInserted.getId());
		publisherDAO.deleteById(publisherInserted.getId());
	}

	@Test
	public void testGetById() {
		Publisher p = new Publisher();
		p.setName("Luis Machado Reis");
		p.setEmail("luis.reis@singularideas.com.br");
		p.setPassword("teste123");
		p.setContact("contact123");

		Publisher publisherInserted = publisherDAO.save(p);
		assertNotNull(publisherInserted);
		assertNotNull(publisherInserted.getId());

		Channel c = new Channel();
		c.setPublisher(publisherInserted);
		c.setName("my 1st Channel");
		c.setDescription("my 1st big description");
		c.setPrice(5.50F);

		Channel channelInserted = channelDAO.save(c);
		assertNotNull(channelInserted);
		assertNotNull(channelInserted.getId());
		
		Item i = new Item();
		i.setChannel(channelInserted);
		i.setTitle("my 1st Item");
		i.setDescription("my 1st Big Description");
		i.setAuthor("Luis Machado Reis");
		i.setFilename("filename.1st");
		
		Item itemInserted = itemDAO.save(i);
		assertNotNull(itemInserted);
		assertNotNull(itemInserted.getId());

		Item itemQuery = itemDAO.getById(itemInserted.getId());
		assertNotNull(itemQuery);
		assertNotNull(itemQuery.getId());
		assertTrue(itemQuery.getId().longValue() == itemInserted.getId().longValue());
		
		itemDAO.deleteById(itemInserted.getId());
		channelDAO.deleteById(channelInserted.getId());
		publisherDAO.deleteById(publisherInserted.getId());
	}

	@Test
	public void testUpdate() {
		Publisher p = new Publisher();
		p.setName("Luis Machado Reis");
		p.setEmail("luis.reis@singularideas.com.br");
		p.setPassword("teste123");
		p.setContact("contact123");

		Publisher publisherInserted = publisherDAO.save(p);
		assertNotNull(publisherInserted);
		assertNotNull(publisherInserted.getId());

		Channel c = new Channel();
		c.setPublisher(publisherInserted);
		c.setName("my 1st Channel");
		c.setDescription("my 1st big description");
		c.setPrice(5.50F);

		Channel channelInserted = channelDAO.save(c);
		assertNotNull(channelInserted);
		assertNotNull(channelInserted.getId());
		
		Item i = new Item();
		i.setChannel(channelInserted);
		i.setTitle("my 1st Item");
		i.setDescription("my 1st Big Description");
		i.setAuthor("Luis Machado Reis");
		i.setFilename("filename.1st");
		
		Item itemInserted = itemDAO.save(i);
		assertNotNull(itemInserted);
		assertNotNull(itemInserted.getId());

		Item itemQuery = itemDAO.getById(itemInserted.getId());
		assertNotNull(itemQuery);
		assertNotNull(itemQuery.getId());
		assertTrue(itemQuery.getId().longValue() == itemInserted.getId().longValue());
		
		itemQuery.setAuthor("ayton senna");
		itemQuery = itemDAO.update(itemQuery);
		assertNotNull(itemQuery);
		assertNotNull(itemQuery.getId());
		assertFalse(itemQuery.getAuthor().equals(itemInserted.getAuthor()));
		
		itemDAO.deleteById(itemInserted.getId());
		channelDAO.deleteById(channelInserted.getId());
		publisherDAO.deleteById(publisherInserted.getId());
	}

	@Test
	public void testGetAll() {
		Publisher p = new Publisher();
		p.setName("Luis Machado Reis");
		p.setEmail("luis.reis@singularideas.com.br");
		p.setPassword("teste123");
		p.setContact("contact123");

		Publisher publisherInserted = publisherDAO.save(p);
		assertNotNull(publisherInserted);
		assertNotNull(publisherInserted.getId());

		Channel c = new Channel();
		c.setPublisher(publisherInserted);
		c.setName("my 1st Channel");
		c.setDescription("my 1st big description");
		c.setPrice(5.50F);

		Channel channelInserted = channelDAO.save(c);
		assertNotNull(channelInserted);
		assertNotNull(channelInserted.getId());
		
		Item i = new Item();
		i.setChannel(channelInserted);
		i.setTitle("my 1st Item");
		i.setDescription("my 1st Big Description");
		i.setAuthor("Luis Machado Reis");
		i.setFilename("filename.1st");
		
		Item itemInserted = itemDAO.save(i);
		assertNotNull(itemInserted);
		assertNotNull(itemInserted.getId());

		i = new Item();
		i.setChannel(channelInserted);
		i.setTitle("my 2nd Item");
		i.setDescription("my 2nd Big Description");
		i.setAuthor("Luis Machado Reis");
		i.setFilename("filename.2nd");
		
		itemInserted = itemDAO.save(i);
		assertNotNull(itemInserted);
		assertNotNull(itemInserted.getId());
		
		List<Item> items = itemDAO.getAll();
		assertNotNull(items);
		assertTrue(items.size() > 1);
		
		for (Item item : items) {
			itemDAO.deleteById(item.getId());
		}
		
		channelDAO.deleteById(channelInserted.getId());
		publisherDAO.deleteById(publisherInserted.getId());
	}

	@Test
	public void testGetByChannel() {
		Publisher p = new Publisher();
		p.setName("Luis Machado Reis");
		p.setEmail("luis.reis@singularideas.com.br");
		p.setPassword("teste123");
		p.setContact("contact123");

		Publisher publisherInserted = publisherDAO.save(p);
		assertNotNull(publisherInserted);
		assertNotNull(publisherInserted.getId());

		Channel c = new Channel();
		c.setPublisher(publisherInserted);
		c.setName("my 1st Channel");
		c.setDescription("my 1st big description");
		c.setPrice(5.50F);

		Channel channelInserted = channelDAO.save(c);
		assertNotNull(channelInserted);
		assertNotNull(channelInserted.getId());
		
		Item i = new Item();
		i.setChannel(channelInserted);
		i.setTitle("my 1st Item");
		i.setDescription("my 1st Big Description");
		i.setAuthor("Luis Machado Reis");
		i.setFilename("filename.1st");
		
		Item itemInserted = itemDAO.save(i);
		assertNotNull(itemInserted);
		assertNotNull(itemInserted.getId());

		i = new Item();
		i.setChannel(channelInserted);
		i.setTitle("my 2nd Item");
		i.setDescription("my 2nd Big Description");
		i.setAuthor("Luis Machado Reis");
		i.setFilename("filename.2nd");
		
		itemInserted = itemDAO.save(i);
		assertNotNull(itemInserted);
		assertNotNull(itemInserted.getId());
		
		List<Item> items = itemDAO.getByChannel(channelInserted);
		assertNotNull(items);
		assertTrue(items.size() > 1);
		
		for (Item item : items) {
			itemDAO.deleteById(item.getId());
		}
		
		channelDAO.deleteById(channelInserted.getId());
		publisherDAO.deleteById(publisherInserted.getId());
	}
	
}
