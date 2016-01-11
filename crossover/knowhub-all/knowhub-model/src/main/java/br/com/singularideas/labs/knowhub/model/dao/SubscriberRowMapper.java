package br.com.singularideas.labs.knowhub.model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import br.com.singularideas.labs.knowhub.common.data.Subscriber;

@Component
public class SubscriberRowMapper implements RowMapper<Subscriber> {

	@Override
	public Subscriber mapRow(ResultSet rs, int row) throws SQLException {
		Subscriber s = new Subscriber();
		s.setId(rs.getLong("id"));
		s.setName(rs.getString("name"));
		s.setPassword(rs.getString("password"));
		s.setEmail(rs.getString("email"));
		
		return s;
	}

}
