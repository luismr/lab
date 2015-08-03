/**
 * 
 */
package notifications.data.repository;

import java.util.List;

import notifications.data.domain.Subscription;
import notifications.data.domain.id.SubscriptionId;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

/**
 * CRUD for Subscriptions
 * @author luismr
 *
 */
public interface SubscriptionRepository extends CrudRepository<Subscription, SubscriptionId> {

	@Query("select o from Subscription o where o.id.categoryId = :categoryId")
	List<Subscription> findAllByCategory(@Param("categoryId") final Integer categoryId);
	

}
