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

import br.com.singularideas.labs.knowhub.common.data.Publisher;
import br.com.singularideas.labs.knowhub.model.ModelException;

@Repository
public class PublisherDAOImpl implements PublisherDAO {

	private static final String INSERT = "insert into publishers (name, email, contact, MD5(password)) values (?,?,?,?)";
	private static final String SELECT = "select * from publishers where id = ?";
	private static final String UPDATE = "update publishers set name=?, email=?, contact=?, password=? where id=?";
	private static final String DELETE = "delete from publishers where id=?";
	private static final String SELECT_ALL = "select * from publishers";
	private static final String SELECT_BY_EMAIL = "select * from publishers where email=?";

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private PublisherRowMapper rowmapper;

	@Override
	public Publisher save(final Publisher p) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		int out = jdbcTemplate.update(new PreparedStatementCreator() {
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(INSERT, new String[] { "id" });
				ps.setString(1, p.getName());
				ps.setString(2, p.getEmail());
				ps.setString(3, p.getContact());
				ps.setString(4, p.getPassword());

				return ps;
			}
		}, keyHolder);

		if (out == 0) {
			throw new ModelException("Cannot insert Publisher [" + p.toString() + "]");
		}

		Publisher subscriber = getById(keyHolder.getKey().longValue());
		return subscriber;
	}

	@Override
	public Publisher getById(long id) {
		Publisher publisher = jdbcTemplate.queryForObject(SELECT, new Object [] { id }, rowmapper);
		return publisher;
	}

	@Override
	public Publisher update(Publisher p) {
		Object [] params = new Object [] {p.getName(), p.getEmail(), p.getPassword(), p.getContact(), p.getId()};
		int out = jdbcTemplate.update(UPDATE, params);
		
		if (out == 0) {
			throw new ModelException("Cannot update Publisher [" + p.toString() + "]");
		}
		
		return p;
	}

	@Override
	public void deleteById(long id) {
		int out = jdbcTemplate.update(DELETE, id);
		
		if (out == 0) {
			throw new ModelException("Cannot delete Publisher witg ID [" + id + "]");
		}
	}

	@Override
	public List<Publisher> getAll() {
		List<Publisher> publishers = jdbcTemplate.query(SELECT_ALL, rowmapper);
		return publishers;
	}

	@Override
	public Publisher getByEmail(String email) {
		Publisher publisher = jdbcTemplate.queryForObject(SELECT_BY_EMAIL, new Object [] { email }, rowmapper);
		return publisher;
	}

}
