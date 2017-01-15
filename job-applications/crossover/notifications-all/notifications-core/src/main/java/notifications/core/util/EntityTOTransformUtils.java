/**
 * 
 */
package notifications.core.util;

import notifications.api.CategoryTO;
import notifications.api.InstanceTO;
import notifications.api.NotificationTO;
import notifications.api.SubscriberTO;
import notifications.api.TokenTO;
import notifications.data.domain.Category;
import notifications.data.domain.Instance;
import notifications.data.domain.Notification;
import notifications.data.domain.Subscriber;
import notifications.data.domain.Token;
import notifications.data.domain.helper.Affirmative;
import notifications.data.domain.helper.Frequency;

/**
 * Tranformation Utils to not expose Data Mapping
 * 
 * @author luismr
 *
 */
public abstract class EntityTOTransformUtils {

	/**
	 * Transform an Entity Token to TO
	 * 
	 * @param token
	 * @return
	 */
	public static TokenTO transformToken(final Token token) {
		TokenTO to = new TokenTO();
		to.setId(token.getId().getTokenId());
		to.setInstanceId(token.getId().getInstanceId());
		to.setName(token.getName());
		to.setPassword(token.getPassword());
		to.setHash(token.getHash());
		
		return to;
	}
	
	/**
	 * Transform an Entity Notification to TO
	 * @param notification
	 * @return
	 */
	public static NotificationTO transformFromNotification(final Notification notification) {
		NotificationTO to = new NotificationTO();
		to.setId(notification.getId());
		to.setInstanceId(notification.getToken().getId().getInstanceId());
		to.setTokenId(notification.getToken().getId().getTokenId());
		to.setCategoryId(notification.getCategory().getId());
		to.setTitle(notification.getTitle());
		to.setData(notification.getData());
		to.setDateCreated(notification.getDateCreated());
		to.setSync(notification.getSync().toString());
		
		return to;
	}

	/**
	 * Transform an Entity Subscriber to TO
	 * @param subscriber
	 * @return
	 */
	public static SubscriberTO transformFromSubscriber(Subscriber subscriber) {
		SubscriberTO to = new SubscriberTO();
		to.setEmail(subscriber.getEmail());
		to.setFrequency(subscriber.getFrequency().toString());
		to.setHash(subscriber.getHash());
		to.setId(subscriber.getId());
		to.setInstanceId(subscriber.getInstance().getId());
		to.setName(subscriber.getName());
		to.setNotifyByPhone(subscriber.getNotifyByPhone().toString());
		to.setPhone(subscriber.getPhone());
		to.setPin(subscriber.getPin());
		
		return to;
	}
	
	/**
	 * Transform a Subscriber TO to Entity
	 * @param to
	 * @return
	 */
	public static Subscriber transformFromSubscriberTO(SubscriberTO to) {
		Subscriber subscriber = new Subscriber();
		subscriber.setEmail(to.getEmail());
		subscriber.setFrequency(Frequency.valueOf(to.getFrequency()));
		subscriber.setHash(to.getHash());
		subscriber.setId(to.getId());

		Instance instance = new Instance();
		instance.setId(to.getInstanceId());
		subscriber.setInstance(instance);
		
		subscriber.setName(to.getName());
		subscriber.setNotifyByPhone(Affirmative.valueOf(to.getNotifyByPhone()));
		subscriber.setPhone(to.getPhone());
		subscriber.setPin(to.getPin());
		
		return subscriber;
	}

	/**
	 * Transform a Category Entity to TO
	 * @param category
	 * @return
	 */
	public static CategoryTO transformFromCategory(Category category) {
		CategoryTO categoryTO = new CategoryTO();
		categoryTO.setId(category.getId());
		categoryTO.setInstanceId(category.getInstance().getId());
		categoryTO.setName(category.getName());
		categoryTO.setDescription(category.getDescription());
		
		return categoryTO;
	}

	/**
	 * Transform a Category TO to Entity
	 * @param to
	 * @return
	 */
	public static Category transformFromCategoryTO(CategoryTO to) {
		Category category = new Category();
		category.setDescription(to.getDescription());
		category.setId(to.getId());
		category.setName(to.getName());
		
		Instance instance = new Instance();
		instance.setId(to.getInstanceId());
		category.setInstance(instance);
		
		return category;
	}

	/**
	 * Transform a Instance Entity to TO
	 * @param instance
	 * @return
	 */
	public static InstanceTO transformFromInstance(Instance instance) {
		InstanceTO instanceTO = new InstanceTO();
		instanceTO.setId(instance.getId());
		instanceTO.setName(instance.getName());
		instanceTO.setDescription(instance.getDescription());
		
		return instanceTO;
	}
	
	/**
	 * Transform an Instance TO to Entity
	 * @param instanceTO
	 * @return
	 */
	public static Instance transformFromInstanceTO(InstanceTO instanceTO) {
		Instance instance = new Instance();
		instance.setId(instanceTO.getId());
		instance.setName(instanceTO.getName());
		instance.setDescription(instanceTO.getDescription());
		
		return instance;
	}
	
}
