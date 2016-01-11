package br.com.singularideas.labs.knowhub.model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import br.com.singularideas.labs.knowhub.common.data.Channel;
import br.com.singularideas.labs.knowhub.common.data.Item;

@Component
public class ItemRowMapper implements RowMapper<Item> {

	@Override
	public Item mapRow(ResultSet rs, int row) throws SQLException {
		Item i = new Item();
		i.setId(rs.getLong("id"));
		
		Channel c = new Channel();
		c.setId(rs.getLong("id_channel"));
		i.setChannel(c);
		
		i.setTitle(rs.getString("title"));
		i.setDescription(rs.getString("description"));
		i.setAuthor(rs.getString("author"));
		i.setFilename(rs.getString("filename"));

		return i;
	}

}
