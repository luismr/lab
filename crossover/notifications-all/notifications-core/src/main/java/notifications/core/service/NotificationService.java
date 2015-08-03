/**
 * 
 */
package notifications.core.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.transaction.Transactional;

import notifications.api.NotificationTO;
import notifications.connector.adaptor.EmailConnector;
import notifications.core.NotificationCoreException;
import notifications.core.util.EntityTOTransformUtils;
import notifications.data.domain.Category;
import notifications.data.domain.Entry;
import notifications.data.domain.Notification;
import notifications.data.domain.Subscriber;
import notifications.data.domain.Token;
import notifications.data.domain.helper.Affirmative;
import notifications.data.domain.helper.EntryStatus;
import notifications.data.repository.CategoryRepository;
import notifications.data.repository.EntryRepository;
import notifications.data.repository.NotificationRepository;
import notifications.data.repository.TokenRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

/**
 * Notification Service
 * 
 * @author luismr
 *
 */
@Service
public class NotificationService {

	@Autowired
	private TokenRepository tokenRepository;
	
	@Autowired
	private NotificationRepository notificationRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private EntryRepository entryRepository;
	
	@Autowired
	private EmailConnector connector;
	
	/**
	 * Send a new notification to be processed
	 * 
	 * @param notification
	 */
	@Transactional
	public void send(final NotificationTO notification) {
		Token token = tokenRepository.getTokenByInstanceAndId(notification.getInstanceId(), notification.getTokenId());
		if (token == null || token.getId() == null || token.getId().getTokenId() == null) {
			throw new NotificationCoreException("Invalid Token");
		}
		
		Category category = categoryRepository.findOne(notification.getCategoryId());
		if (category == null) {
			throw new NotificationCoreException("Invalid Category");
		}
		
		Notification subject = new Notification();
		subject.setTitle(notification.getTitle());
		subject.setData(notification.getData());
		subject.setDateCreated(new Date());
		subject.setSync(Affirmative.NO);
		subject.setCategory(category);
		subject.setToken(token);
		
		notificationRepository.save(subject);
	}

	/**
	 * Push Notifications for Subscriber
	 * @param subscriber
	 * @param notification
	 */
	@Async
	public void push(Subscriber subscriber, Notification notification) {
		connector.setSubscriber(EntityTOTransformUtils.transformFromSubscriber(subscriber));
		connector.setNotification(EntityTOTransformUtils.transformFromNotification(notification));
		connector.send();
	}
	
	/**
	 * Flush all done notifications and entries older then 30 days
	 * every 1st day of month
	 */
	@Transactional
	@Scheduled(cron="0 0 0 1 * *")
	public void flushAllDone() {
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setTime(new Date());
		calendar.add(Calendar.DAY_OF_YEAR, -30);
		
		List<Notification> notifications = notificationRepository.findAllBySyncAndOlderThenDate(Affirmative.YES, calendar.getTime());
		for (Notification notification : notifications) {
			List<Entry> entries = entryRepository.findAllByNotificationIdAndSyncAndOlderThenDate(notification.getId(), EntryStatus.DONE, calendar.getTime());
			
			if (entries.isEmpty() == false) {
				entryRepository.delete(entries);
			}
		}
		
		notificationRepository.delete(notifications);
	}

	/**
	 * Get all Notifications by Sync and InstanceId 
	 * @param intanceId
	 * @return
	 */
	public List<NotificationTO> getPendingNotifications(Integer instanceId) {
		List<NotificationTO> tos = new ArrayList<NotificationTO>();
		
		List<Notification> notifications = notificationRepository.findAllBySyncAndInstance(Affirmative.NO, instanceId);
		for (Notification notification : notifications) {
			tos.add(EntityTOTransformUtils.transformFromNotification(notification));
		}
		
		return tos;
	}
	
}
