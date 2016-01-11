package br.com.singularideas.labs.knowhub.model.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.singularideas.labs.knowhub.common.data.Subscriber;
import br.com.singularideas.labs.knowhub.common.data.Subscription;
import br.com.singularideas.labs.knowhub.common.vo.Profile;
import br.com.singularideas.labs.knowhub.common.vo.StandardProfile;
import br.com.singularideas.labs.knowhub.model.ModelException;
import br.com.singularideas.labs.knowhub.model.dao.SubscriberDAO;
import br.com.singularideas.labs.knowhub.model.dao.SubscriptionDAO;

@Service
public class AuthenticationService {

	@Autowired
	private SubscriberDAO subscriberDAO;
	
	@Autowired
	private SubscriptionDAO subscriptionDAO;
	
	public Profile login(final String email, final String password) {
		Profile session = null;
		
		try {
			Subscriber subscriber = subscriberDAO.getByEmail(email);
			
			if (! password.equals(subscriber.getPassword())) {
				throw new ModelException();
			}
			
			List<Subscription> subscriptions = subscriptionDAO.getBySubscriber(subscriber);
			
			session = new StandardProfile();
			session.setCreated(new Date());
			session.setSubscriber(subscriber);
			session.setSubscriptions(subscriptions);
		} catch (ModelException me) {
			throw me;
		} catch (EmptyResultDataAccessException erdae) {
			throw new ModelException(String.format("Invalid credentials for [%s]", email, password));
		} catch (Exception e) {
			throw new ModelException(String.format("Cannot authenticate [%s]", e.getMessage()), e);
		}
		
		return session;
	}
}
