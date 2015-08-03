/**
 * 
 */
package notifications.core.service;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import notifications.connector.SendEmailException;
import notifications.connector.SendVoiceException;
import notifications.data.domain.Entry;
import notifications.data.domain.Notification;
import notifications.data.domain.Subscriber;
import notifications.data.domain.Subscription;
import notifications.data.domain.helper.Affirmative;
import notifications.data.domain.helper.EntryStatus;
import notifications.data.domain.id.EntryID;
import notifications.data.repository.EntryRepository;
import notifications.data.repository.NotificationRepository;
import notifications.data.repository.SubscriberRepository;
import notifications.data.repository.SubscriptionRepository;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

/**
 * Subscription Service
 * 
 * @author luismr
 *
 */
@Service
public class SubscriptionService {
	
	private Logger logger = Logger.getLogger(SubscriptionService.class.getSimpleName());
	
	@Autowired
	private SubscriberRepository subscriberRepository;
	
	@Autowired
	private SubscriptionRepository subscriptionRepository;
	
	@Autowired
	private EntryRepository entryRepository;
	
	@Autowired
	private NotificationRepository notificationRepository;
	
	@Autowired
	private NotificationService notificationService;
	
	/**
	 * Load all unsynchronized notifications to Subscription Entries
	 */
	@Transactional
	@Scheduled(cron="00 */4 * * * *")
	public synchronized void loadUnsychronizedNotifications() {		
		logger.info("Executing loadUnsynchronyzedNotifications()");

		List<Notification> notifications = notificationRepository.findAllBySync(Affirmative.NO);

		for (Notification notification : notifications) {
			List<Subscription> subscriptions = subscriptionRepository.findAllByCategory(notification.getCategory().getId());
			for (Subscription subscription : subscriptions) {
				Entry entry = new Entry();
				
				EntryID id = new EntryID();
				id.setCategoryId(notification.getCategory().getId());
				id.setNotificationId(notification.getId());
				id.setSubscriberId(subscription.getId().getSubscriberId());
				
				entry.setId(id);
				entry.setDateCreated(new Date());
				entry.setDateSchedule(subscription.getSubscriber().getFrequency().calculateNextDelivery());
				entry.setStatus(EntryStatus.NEW);
				
				entryRepository.save(entry);
			}
			
			notification.setSync(Affirmative.YES);
			notificationRepository.save(notification);
		}

		logger.info("finish loadUnsynchronyzedNotifications()");
	}
	
	/**
	 * Select all unsynchronized subscriptions entries to Process
	 * 
	 * 1. Select all Entries where status = NEW and date > dateSchedule
	 * 2. Set PROCESSING status
	 * 
	 * All PROCESSING Entries will be processed during next minutes to avoid
	 * freezing, spam, and another boring things about email notifications 
	 * 
	 */
	@Transactional
	@Scheduled(cron="00 */6 * * * *")
	public synchronized void selectNewEntriesToProcess() {
		logger.info("Executing selectNewEntriesToProcess()");

		List<Entry> entries = entryRepository.findAllBySyncAndDateSchedule(EntryStatus.NEW, new Date());
		if (entries.size() > 0) {
			logger.info("Processing " + entries.size() + " notification entries");
		}

		for (Entry entry : entries) {
			entry.setStatus(EntryStatus.PROCESSING);
			entry.setDateUpdated(new Date());
			entryRepository.save(entry);
		}

		logger.info("finish selectNewEntriesToProcess()");
	}
	
	/**
	 * Push all PROCESSING Entries to their Subscribers
	 * 
	 * If happen an exception, set status = ERROR
	 */
	@Transactional
	@Scheduled(cron="00 */8 * * * *")
	public synchronized void push() {
		logger.info("Starting push()");
		
		List<Entry> entries = entryRepository.findAllBySyncAndDateSchedule(EntryStatus.PROCESSING, new Date());
		if (entries.size() > 0) {
			logger.info("Pushing " + entries.size() + " notification entries");
		}
		
		for (Entry entry : entries) {
			EntryStatus status = entry.getStatus();
			
			try {
				Subscriber subscriber = entry.getSubscription().getSubscriber();
				Notification notification = entry.getNotification();
				
				notificationService.push(subscriber, notification);
				logger.info("sent e-mail to subscriber [" + subscriber.getEmail() + "]");
				
				status = EntryStatus.DONE;
			} catch (SendEmailException e) {
				logger.error(e.getMessage(), e);
				status = EntryStatus.ERROR_MAIL;
			} catch (SendVoiceException e) {
				logger.error(e.getMessage(), e);
				status = EntryStatus.ERROR_PHONE;
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				status = EntryStatus.ERROR;
			}
			
			entry.setDateUpdated(new Date());
			entry.setStatus(status);
			
			entryRepository.save(entry);
		}
		
		logger.info("Finish pushing notification entries");
	}

}
