package br.com.singularideas.labs.knowhub.model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import br.com.singularideas.labs.knowhub.common.data.Channel;
import br.com.singularideas.labs.knowhub.common.data.Subscriber;
import br.com.singularideas.labs.knowhub.common.data.Subscription;
import br.com.singularideas.labs.knowhub.common.data.SubscriptionStatus;

@Component
public class SubscriptionRowMapper implements RowMapper<Subscription> {

	@Autowired
	private SubscriberDAO subscriberDAO;
	
	@Autowired
	private ChannelDAO channelDAO;
	
	@Override
	public Subscription mapRow(ResultSet rs, int row) throws SQLException {
		Subscription subscription = new Subscription();
		subscription.setId(rs.getLong("id"));
		
		Subscriber s = subscriberDAO.getById(rs.getLong("id_subscriber"));
		subscription.setSubscriber(s);
		
		Channel channel = channelDAO.getById(rs.getLong("id_channel"));
		subscription.setChannel(channel);
		
		subscription.setCreated(rs.getDate("created"));
		subscription.setUpdated(rs.getDate("updated"));
		subscription.setExpires(rs.getDate("expires"));
		
		SubscriptionStatus status = SubscriptionStatus.valueOf(rs.getString("status"));
		subscription.setStatus(status);
		
		return subscription;
	}

}
