package br.com.singularideas.labs.knowhub.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import br.com.singularideas.labs.knowhub.common.data.Subscriber;
import br.com.singularideas.labs.knowhub.common.data.Subscription;
import br.com.singularideas.labs.knowhub.model.ModelException;

@Repository
public class SubscriptionDAOImpl implements SubscriptionDAO {

	private static final String INSERT = "insert into subscriptions (id_subscriber, id_channel, created, updated, expires, status) values (?,?,?,?,?,?)";
	private static final String SELECT = "select * from subscriptions where id = ?";
	private static final String UPDATE = "update subscriptions set id_subscriber=?, id_channel=?, updated=?, expires=?, status=? where id=?";
	private static final String DELETE = "delete from subscriptions where id=?";
	private static final String SELECT_ALL = "select * from subscriptions";
	private static final String SELECT_BY_SUBSCRIBER = "select * from subscriptions where id_subscriber=?";

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private SubscriptionRowMapper rowmapper;

	@Override
	public Subscription save(final Subscription subscription) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		int out = jdbcTemplate.update(new PreparedStatementCreator() {
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(INSERT, new String[] { "id" });
				ps.setLong(1,  subscription.getSubscriber().getId());
				ps.setLong(2,  subscription.getChannel().getId());
				
				java.sql.Date now = new java.sql.Date(System.currentTimeMillis());
				ps.setDate(3, now);
				ps.setDate(4, now);
				ps.setDate(5, now);
				
				ps.setString(6, subscription.getStatus().toString());

				return ps;
			}
		}, keyHolder);

		if (out == 0) {
			throw new ModelException("Cannot insert Subscription [" + subscription.toString() + "]");
		}

		Subscription s = getById(keyHolder.getKey().longValue());
		return s;
	}

	@Override
	public Subscription getById(long id) {
		Subscription subscription = jdbcTemplate.queryForObject(SELECT, new Object [] { id }, rowmapper);
		return subscription;
	}

	@Override
	public Subscription update(Subscription s) {
		Object [] params = new Object [] {s.getSubscriber().getId(), s.getChannel(), new Date(), s.getExpires(), s.getStatus().toString(), s.getId()};
		int out = jdbcTemplate.update(UPDATE, params);
		
		if (out == 0) {
			throw new ModelException("Cannot update Channel [" + s.toString() + "]");
		}
		
		return s;
	}

	@Override
	public void deleteById(long id) {
		int out = jdbcTemplate.update(DELETE, id);
		
		if (out == 0) {
			throw new ModelException("Cannot delete Channel witg ID [" + id + "]");
		}
	}

	@Override
	public List<Subscription> getAll() {
		List<Subscription> subscriptions = jdbcTemplate.query(SELECT_ALL, rowmapper);
		return subscriptions;
	}

	@Override
	public List<Subscription> getBySubscriber(Subscriber s) {
		List<Subscription> subscriptions = jdbcTemplate.query(SELECT_BY_SUBSCRIBER, new Object [] { s.getId()}, rowmapper);
		return subscriptions;
	}

}
