package br.com.singularideas.labs.knowhub.model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import br.com.singularideas.labs.knowhub.common.crypto.CryptoUtils;
import br.com.singularideas.labs.knowhub.common.data.Subscriber;
import br.com.singularideas.labs.knowhub.model.ModelException;

@Component
public class SubscriberRowMapper implements RowMapper<Subscriber> {

	@Override
	public Subscriber mapRow(ResultSet rs, int row) throws SQLException {
		Subscriber s = new Subscriber();
		try {
			s.setId(rs.getLong("id"));
			s.setName(rs.getString("name"));
			s.setPassword(CryptoUtils.crypto(rs.getString("password")));
			s.setEmail(rs.getString("email"));
		} catch (Exception e) {
			throw new ModelException(e.getMessage(), e);
		}
		
		return s;
	}

}
