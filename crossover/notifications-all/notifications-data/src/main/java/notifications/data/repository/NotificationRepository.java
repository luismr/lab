/**
 * 
 */
package notifications.data.repository;

import java.util.Date;
import java.util.List;

import notifications.data.domain.Notification;
import notifications.data.domain.helper.Affirmative;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

/**
 * CRUD for Notifications
 * @author luismr
 *
 */
public interface NotificationRepository extends CrudRepository<Notification, Integer> {

	@Query("select o from Notification o where o.sync = :sync")
	List<Notification> findAllBySync(@Param("sync") final Affirmative sync);

	@Query("select o from Notification o where o.sync = :sync and o.dateCreated < :date")
	List<Notification> findAllBySyncAndOlderThenDate(@Param("sync") Affirmative sync, @Param("date") Date date);

	@Query("select o from Notification o where o.sync = :sync and o.token.id.instanceId = :instanceId")
	List<Notification> findAllBySyncAndInstance(@Param("sync") Affirmative sync, @Param("instanceId") Integer instanceId);
	
}
