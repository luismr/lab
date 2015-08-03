/**
 * 
 */
package notifications.data.repository;

import notifications.data.domain.Subscriber;

import org.springframework.data.repository.CrudRepository;

/**
 * CRUD for Subscribers
 * @author luismr
 *
 */
public interface SubscriberRepository extends CrudRepository<Subscriber, Long> {
}
