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
import br.com.singularideas.labs.knowhub.common.data.Publisher;
import br.com.singularideas.labs.knowhub.model.ModelException;

@Repository
public class ChannelDAOImpl implements ChannelDAO {

	private static final String INSERT = "insert into channels (id_publisher, name, description, price) values (?,?,?,?)";
	private static final String SELECT = "select * from channels where id = ?";
	private static final String UPDATE = "update channels set id_publisher=?, name=?, description=?, price=? where id=?";
	private static final String DELETE = "delete from channels where id=?";
	private static final String SELECT_ALL = "select * from channels";
	private static final String SELECT_BY_PUBLISHER = "select * from channels where id_publisher=?";

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private ChannelRowMapper rowmapper;

	@Override
	public Channel save(final Channel c) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		int out = jdbcTemplate.update(new PreparedStatementCreator() {
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(INSERT, new String[] { "id" });
				ps.setLong(1,  c.getPublisher().getId());
				ps.setString(2, c.getName());
				ps.setString(3, c.getDescription());
				ps.setFloat(4, c.getPrice());

				return ps;
			}
		}, keyHolder);

		if (out == 0) {
			throw new ModelException("Cannot insert Channel [" + c.toString() + "]");
		}

		Channel channel = getById(keyHolder.getKey().longValue());
		return channel;
	}

	@Override
	public Channel getById(long id) {
		Channel channel = jdbcTemplate.queryForObject(SELECT, new Object [] { id }, rowmapper);
		return channel;
	}

	@Override
	public Channel update(Channel c) {
		Object [] params = new Object [] {c.getPublisher().getId(), c.getName(), c.getDescription(), c.getPrice(), c.getId()};
		int out = jdbcTemplate.update(UPDATE, params);
		
		if (out == 0) {
			throw new ModelException("Cannot update Channel [" + c.toString() + "]");
		}
		
		return c;
	}

	@Override
	public void deleteById(long id) {
		int out = jdbcTemplate.update(DELETE, id);
		
		if (out == 0) {
			throw new ModelException("Cannot delete Channel witg ID [" + id + "]");
		}
	}

	@Override
	public List<Channel> getAll() {
		List<Channel> publishers = jdbcTemplate.query(SELECT_ALL, rowmapper);
		return publishers;
	}

	@Override
	public List<Channel> getByPublisher(Publisher p) {
		List<Channel> publishers = jdbcTemplate.query(SELECT_BY_PUBLISHER, new Object [] { p.getId()}, rowmapper);
		return publishers;
	}

}
