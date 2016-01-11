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

import br.com.singularideas.labs.knowhub.common.data.Subscriber;
import br.com.singularideas.labs.knowhub.model.ModelException;

@Repository
public class SubscriberDAOImpl implements SubscriberDAO {

	private static final String INSERT = "insert into subscribers (name, email, MD5(password)) values (?,?,?)";
	private static final String SELECT = "select * from subscribers where id = ?";
	private static final String UPDATE = "update subscribers set name=?, email=?, password=? where id=?";
	private static final String DELETE = "delete from subscribers where id=?";
	private static final String SELECT_ALL = "select * from subscribers";
	private static final String SELECT_BY_EMAIL = "select * from subscribers where email=?";

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private SubscriberRowMapper rowmapper;

	@Override
	public Subscriber save(final Subscriber s) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		int out = jdbcTemplate.update(new PreparedStatementCreator() {
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(INSERT, new String[] { "id" });
				ps.setString(1, s.getName());
				ps.setString(2, s.getEmail());
				ps.setString(3, s.getPassword());

				return ps;
			}
		}, keyHolder);

		if (out == 0) {
			throw new ModelException("Cannot insert Subscriber [" + s.toString() + "]");
		}

		Subscriber subscriber = getById(keyHolder.getKey().longValue());
		return subscriber;
	}

	@Override
	public Subscriber getById(long id) {
		Subscriber subscriber = jdbcTemplate.queryForObject(SELECT, new Object [] { id }, rowmapper);
		return subscriber;
	}

	@Override
	public Subscriber update(Subscriber s) {
		Object [] params = new Object [] {s.getName(), s.getEmail(), s.getPassword(), s.getId()};
		int out = jdbcTemplate.update(UPDATE, params);
		
		if (out == 0) {
			throw new ModelException("Cannot update Subscriber [" + s.toString() + "]");
		}
		
		return s;
	}

	@Override
	public void deleteById(long id) {
		int out = jdbcTemplate.update(DELETE, id);
		
		if (out == 0) {
			throw new ModelException("Cannot delete Subscriber witg ID [" + id + "]");
		}
	}

	@Override
	public List<Subscriber> getAll() {
		List<Subscriber> subscribers = jdbcTemplate.query(SELECT_ALL, rowmapper);
		return subscribers;
	}

	@Override
	public Subscriber getByEmail(String email) {
		Subscriber subscriber = jdbcTemplate.queryForObject(SELECT_BY_EMAIL, new Object [] { email }, rowmapper);
		return subscriber;
	}

}
