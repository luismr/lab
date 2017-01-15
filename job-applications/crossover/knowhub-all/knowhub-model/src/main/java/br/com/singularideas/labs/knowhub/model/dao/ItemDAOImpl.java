package br.com.singularideas.labs.knowhub.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import br.com.singularideas.labs.knowhub.common.data.Channel;
import br.com.singularideas.labs.knowhub.common.data.Item;
import br.com.singularideas.labs.knowhub.model.ModelException;

@Repository
public class ItemDAOImpl implements ItemDAO {

	private static final String INSERT = "insert into items (id_channel, title, description, author, filename) values (?,?,?,?,?)";
	private static final String SELECT = "select * from items where id = ?";
	private static final String UPDATE = "update items set id_channel=?, title=?, description=?, author=?, filename=? where id=?";
	private static final String DELETE = "delete from items where id=?";
	private static final String SELECT_ALL = "select * from items";
	private static final String SELECT_BY_CHANNEL = "select * from items where id_channel=?";

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private ItemRowMapper rowmapper;

	@Override
	public Item save(final Item i) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		int out = jdbcTemplate.update(new PreparedStatementCreator() {
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(INSERT, new String[] { "id" });
				ps.setLong(1,  i.getChannel().getId());
				ps.setString(2, i.getTitle());
				ps.setString(3, i.getDescription());
				ps.setString(4, i.getAuthor());
				ps.setString(5, i.getFilename());

				return ps;
			}
		}, keyHolder);

		if (out == 0) {
			throw new ModelException("Cannot insert Item [" + i.toString() + "]");
		}

		Item item = getById(keyHolder.getKey().longValue());
		return item;
	}

	@Override
	public Item getById(long id) {
		Item item = jdbcTemplate.queryForObject(SELECT, new Object [] { id }, rowmapper);
		return item;
	}

	@Override
	public Item update(Item i) {
		Object [] params = new Object [] {i.getChannel().getId(), i.getTitle(), i.getDescription(), i.getAuthor(), i.getFilename(), i.getId()};
		int out = jdbcTemplate.update(UPDATE, params);
		
		if (out == 0) {
			throw new ModelException("Cannot update Item [" + i.toString() + "]");
		}
		
		return i;
	}

	@Override
	public void deleteById(long id) {
		int out = jdbcTemplate.update(DELETE, id);
		
		if (out == 0) {
			throw new ModelException("Cannot delete Item witg ID [" + id + "]");
		}
	}

	@Override
	public List<Item> getAll() {
		List<Item> items = jdbcTemplate.query(SELECT_ALL, rowmapper);
		return items;
	}

	@Override
	public List<Item> getByChannel(Channel c) {
		List<Item> items = jdbcTemplate.query(SELECT_BY_CHANNEL, new Object [] { c.getId()}, rowmapper);
		return items;
	}

}
