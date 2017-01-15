/**
 * 
 */
package notifications.core.service;

import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.transaction.Transactional;

import notifications.api.SubscriberTO;
import notifications.api.SubscriptionTO;
import notifications.api.TokenTO;
import notifications.api.util.StringUtils;
import notifications.core.NotificationCoreException;
import notifications.core.util.EntityTOTransformUtils;
import notifications.data.domain.Category;
import notifications.data.domain.Entry;
import notifications.data.domain.Instance;
import notifications.data.domain.Subscriber;
import notifications.data.domain.Subscription;
import notifications.data.domain.Token;
import notifications.data.domain.helper.Affirmative;
import notifications.data.domain.helper.Frequency;
import notifications.data.domain.id.SubscriptionId;
import notifications.data.repository.CategoryRepository;
import notifications.data.repository.EntryRepository;
import notifications.data.repository.InstanceRepository;
import notifications.data.repository.SubscriberRepository;
import notifications.data.repository.SubscriptionRepository;
import notifications.data.repository.TokenRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Subscriber Service
 * 
 * @author luismr
 *
 */
@Service
public class SubscriberService {

	@Autowired
	private SubscriberRepository subscriberRepository;
	
	@Autowired
	private SubscriptionRepository subscriptionRepository;
	
	@Autowired
	private InstanceRepository instanceRepository;
	
	@Autowired
	private TokenRepository tokenRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private TokenService tokenService;
	
	@Autowired
	private EntryRepository entryRepository;
	
	public SubscriberService() {}
	
	/**
	 * Read a Subscriber
	 * 
	 * @param tokenTO
	 * @param subscriberId
	 * @return
	 */
	public SubscriberTO read(final TokenTO tokenTO, final Long subscriberId) {
		Subscriber subscriber = subscriberRepository.findOne(subscriberId);
		
		if (subscriber == null) {
			throw new NotificationCoreException("Subscriber ID [" + subscriberId + "] doesn't exist!");
		}
		
		return EntityTOTransformUtils.transformFromSubscriber(subscriber);
	}
	
	/**
	 * Create a new Subcriber
	 * @param tokenTO
	 * @param subscriberTO
	 * @return
	 */
	@Transactional
	public SubscriberTO create(final TokenTO tokenTO, final SubscriberTO subscriberTO) {
		if (subscriberTO == null) {
			throw new NotificationCoreException("subscriberTO must be not null");
		} else if (subscriberTO.getId() != null) {
			throw new NotificationCoreException("subscriberTO.id must be null to create a new one");
		} else if (tokenTO == null) {
			throw new NotificationCoreException("tokenTO must be valid");
		} else if (tokenService.isValid(tokenTO.getId(), tokenTO.getHash()) == false) {
			throw new NotificationCoreException("tokenTO is invalid!");
		}

		Subscriber subscriber = EntityTOTransformUtils.transformFromSubscriberTO(subscriberTO);
		
		Token token = tokenRepository.getTokenByIdAndKey(tokenTO.getId(), tokenTO.getHash());
		Instance instance = instanceRepository.findOne(token.getInstance().getId());
		subscriber.setInstance(instance);
		
		subscriber = subscriberRepository.save(subscriber);
		
		return  EntityTOTransformUtils.transformFromSubscriber(subscriber);
	}
	
	/**
	 * Update an existing Subscriber
	 * 
	 * @param subscriberTO
	 * @return
	 */
	@Transactional
	public SubscriberTO update(final TokenTO tokenTO, final SubscriberTO subscriberTO) {
		if (subscriberTO == null) {
			throw new NotificationCoreException("to must be not null");
		} else if (subscriberTO.getId() == null) {
			throw new NotificationCoreException("id must be not null to update");
		} else if (tokenService.isValid(tokenTO.getId(), tokenTO.getHash()) == false) {
			throw new NotificationCoreException("tokenTO is invalid!");
		}
		
		Token token = tokenRepository.getTokenByIdAndKey(tokenTO.getId(), tokenTO.getHash());
		Instance instance = token.getInstance();

		Subscriber subscriber = subscriberRepository.findOne(subscriberTO.getId());
		if (subscriber == null || subscriber.getInstance() == null || subscriber.getInstance().getId() == null) {
			throw new NotificationCoreException("id [" + subscriberTO.getId() + "] doesn't exist!");
		}
		
		
		if (instance.getId().equals(subscriber.getInstance().getId()) == false) {
			throw new NotificationCoreException("you are trying to update a record from another instance!");
		} else if (subscriber.getHash().equals(subscriberTO.getHash()) == false) {
			throw new NotificationCoreException("Pin & Hash don't match");
		}
		
		subscriber.setName(subscriberTO.getName());
		subscriber.setFrequency(Frequency.valueOf(subscriberTO.getFrequency()));
		subscriber.setPin(subscriberTO.getPin());
		
		try {
			subscriber.setHash(StringUtils.md5(subscriberTO.getPin()));
		} catch (NoSuchAlgorithmException e) {
			throw new NotificationCoreException(e);
		}
		
		subscriber.setNotifyByPhone(Affirmative.valueOf(subscriberTO.getNotifyByPhone()));
		subscriber.setPhone(subscriberTO.getPhone());
		
		subscriber = subscriberRepository.save(subscriber);
		
		return EntityTOTransformUtils.transformFromSubscriber(subscriber);
	}
	
	/**
	 * Delete a Subscriber and all his subscriptions
	 * @param subscriberID
	 */
	@Transactional
	public void delete(final TokenTO tokenTO, final Long subscriberID) {
		if (subscriberID == null) {
			throw new NotificationCoreException("id must be not null!");
		} else if (tokenService.isValid(tokenTO.getId(), tokenTO.getHash()) == false) {
			throw new NotificationCoreException("tokenTO is invalid!");
		}
		
		try {
			Subscriber subscriber = subscriberRepository.findOne(subscriberID);
			if (subscriber == null || subscriber.getInstance() == null || subscriber.getInstance().getId() == null) {
				throw new NotificationCoreException("id [" + subscriberID + "] doesn't exist!");
			}
			
			Token token = tokenRepository.getTokenByIdAndKey(tokenTO.getId(), tokenTO.getHash());
			Instance instance = token.getInstance();
			
			if (instance.getId().equals(subscriber.getInstance().getId()) == false) {
				throw new NotificationCoreException("you are trying to update a record from another instance!");
			}
			
			List<Subscription> subscriptions = subscriber.getSubscriptions();
			for (Subscription subscription : subscriptions) {
				List<Entry> entries = entryRepository.findAllBySubscription(subscription.getSubscriber().getId(), subscription.getCategory().getId()); 
				entryRepository.delete(entries);
				subscriptionRepository.delete(subscription);
			}
			
			subscriberRepository.delete(subscriber.getId());
		} catch (Exception e) {
			e.printStackTrace();
			throw new NotificationCoreException(e);
		}
	}
	
	/**
	 * Follow a Category
	 * @param tokenTO
	 * @param subscriberID
	 * @param categoryID
	 * @return 
	 */
	@Transactional
	public SubscriptionTO follow(final TokenTO tokenTO, final Long subscriberID, final Integer categoryID) {
		if (subscriberID == null || categoryID == null) {
			throw new NotificationCoreException("subscriberId and categoryID must be not null!");
		} else if (tokenService.isValid(tokenTO.getId(), tokenTO.getHash()) == false) {
			throw new NotificationCoreException("tokenTO is invalid!");
		}
		
		SubscriptionTO subscriptionTO = null;
		
		try {
			Subscriber subscriber = subscriberRepository.findOne(subscriberID);
			if (subscriber == null || subscriber.getInstance() == null || subscriber.getInstance().getId() == null) {
				throw new NotificationCoreException("id [" + subscriberID + "] doesn't exist!");
			}
			
			Token token = tokenRepository.getTokenByIdAndKey(tokenTO.getId(), tokenTO.getHash());
			Instance instance = token.getInstance();
			
			if (instance.getId().equals(subscriber.getInstance().getId()) == false) {
				throw new NotificationCoreException("you are trying to update a record from another instance!");
			}
			
			Category category = categoryRepository.findOne(categoryID);

			SubscriptionId subscriptionId = new SubscriptionId();
			subscriptionId.setCategoryId(category.getId());
			subscriptionId.setSubscriberId(subscriber.getId());
			
			Subscription subscription = new Subscription();
			subscription.setId(subscriptionId);
			
			subscriptionRepository.save(subscription);
			
			subscriptionTO = new SubscriptionTO();
			subscriptionTO.setCategoryId(categoryID);
			subscriptionTO.setCustomerId(subscriberID);
		} catch (Exception e) {
			throw new NotificationCoreException(e);
		}
		
		return subscriptionTO;
	}
 	
	/**
	 * Unfollow a Category
	 * 
	 * @param tokenTO
	 * @param subscriberID
	 * @param categoryID
	 * @return 
	 */
	@Transactional
	public SubscriptionTO unfollow(final TokenTO tokenTO, final Long subscriberID, final Integer categoryID) {
		if (subscriberID == null || categoryID == null) {
			throw new NotificationCoreException("subscriberId and categoryID must be not null!");
		} else if (tokenService.isValid(tokenTO.getId(), tokenTO.getHash()) == false) {
			throw new NotificationCoreException("tokenTO is invalid!");
		}
		
		SubscriptionTO subscriptionTO = null;

		try {
			Subscriber subscriber = subscriberRepository.findOne(subscriberID);
			
			Token token = tokenRepository.getTokenByIdAndKey(tokenTO.getId(), tokenTO.getHash());
			Instance instance = token.getInstance();
			
			if (instance.getId().equals(subscriber.getInstance().getId()) == false) {
				throw new NotificationCoreException("you are trying to update a record from another instance!");
			}
			
			SubscriptionId subscriptionID = new SubscriptionId(subscriberID, categoryID);
			subscriptionRepository.delete(subscriptionID);
			
			subscriptionTO = new SubscriptionTO();
			subscriptionTO.setCategoryId(categoryID);
			subscriptionTO.setCustomerId(subscriberID);
		} catch (Exception e) {
			throw new NotificationCoreException(e);
		}
		
		return subscriptionTO;
	}
}
