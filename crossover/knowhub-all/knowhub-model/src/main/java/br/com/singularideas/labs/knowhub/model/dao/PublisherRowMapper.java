package br.com.singularideas.labs.knowhub.model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import br.com.singularideas.labs.knowhub.common.crypto.CryptoUtils;
import br.com.singularideas.labs.knowhub.common.data.Publisher;
import br.com.singularideas.labs.knowhub.model.ModelException;

@Component
public class PublisherRowMapper implements RowMapper<Publisher> {

	@Override
	public Publisher mapRow(ResultSet rs, int row) throws SQLException {
		Publisher p = new Publisher();
		try {
			p.setId(rs.getLong("id"));
			p.setName(rs.getString("name"));
			p.setContact(rs.getString("contact"));
			p.setPassword(CryptoUtils.crypto(rs.getString("password")));
			p.setEmail(rs.getString("email"));
		} catch (Exception e) {
			throw new ModelException(e.getMessage(), e);
		}
		
		return p;
	}

}
