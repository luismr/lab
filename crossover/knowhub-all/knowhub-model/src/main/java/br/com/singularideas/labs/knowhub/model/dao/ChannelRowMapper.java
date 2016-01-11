package br.com.singularideas.labs.knowhub.model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import br.com.singularideas.labs.knowhub.common.data.Channel;
import br.com.singularideas.labs.knowhub.common.data.Item;
import br.com.singularideas.labs.knowhub.common.data.Publisher;

@Component
public class ChannelRowMapper implements RowMapper<Channel> {

	@Autowired
	private PublisherDAO publisherDAO;
	
	@Autowired
	private ItemDAO itemDAO;
	
	@Override
	public Channel mapRow(ResultSet rs, int row) throws SQLException {
		Channel c = new Channel();
		c.setId(rs.getLong("id"));
		
		Publisher p = publisherDAO.getById(rs.getLong("id_publisher"));
		c.setPublisher(p);
		
		c.setName(rs.getString("name"));
		c.setDescription(rs.getString("description"));
		
		List<Item> items = itemDAO.getByChannel(c);
		c.setItems(items);

		return c;
	}

}
