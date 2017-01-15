package br.com.singularideas.labs.knowhub.model.dao;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import br.com.singularideas.labs.knowhub.common.data.Channel;
import br.com.singularideas.labs.knowhub.common.data.Publisher;
import br.com.singularideas.labs.knowhub.model.ModelConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { ModelConfig.class })
public class ItemDAOImplTest {

	@Autowired
	private PublisherDAO publisherDAO;
	
	@Autowired
	private ChannelDAO channelDAO;

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

		Channel channelInserted = channelDAO.save(c);
		assertNotNull(channelInserted);
		assertNotNull(channelInserted.getId());
		
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

		Channel channelInserted = channelDAO.save(c);
		assertNotNull(channelInserted);
		assertNotNull(channelInserted.getId());

		Channel channelSelected = channelDAO.getById(channelInserted.getId());
		assertNotNull(channelSelected);
		assertNotNull(channelSelected.getId());
		
		assertTrue(channelSelected.equals(channelInserted));
		
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

		Channel channelInserted = channelDAO.save(c);
		assertNotNull(channelInserted);
		assertNotNull(channelInserted.getId());

		channelInserted.setName("my 2nd Channel");
		Channel channelUpdated = channelDAO.update(channelInserted);
		assertNotNull(channelUpdated);
		assertNotNull(channelUpdated.getId());
		
		assertFalse(c.getName().equals(channelUpdated.getName()));
		
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

		Channel channelInserted = channelDAO.save(c);
		assertNotNull(channelInserted);
		assertNotNull(channelInserted.getId());

		List<Channel> channels = channelDAO.getAll();
		assertNotNull(channels);
		assertTrue(channels.size() > 0);
		
		channelDAO.deleteById(channelInserted.getId());
		publisherDAO.deleteById(publisherInserted.getId());
	}

	@Test
	public void testGetByPublisherl() {
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

		Channel channelInserted = channelDAO.save(c);
		assertNotNull(channelInserted);
		assertNotNull(channelInserted.getId());

		c = new Channel();
		c.setPublisher(publisherInserted);
		c.setName("my 2nd Channel");
		c.setDescription("my 1st big description");

		channelInserted = channelDAO.save(c);
		assertNotNull(channelInserted);
		assertNotNull(channelInserted.getId());

		List<Channel> channels = channelDAO.getByPublisher(publisherInserted);
		assertNotNull(channels);
		assertTrue(channels.size() > 1);
		
		for (Channel channel : channels) {
			channelDAO.deleteById(channel.getId());
		}
		
		publisherDAO.deleteById(publisherInserted.getId());
	}
	
}
