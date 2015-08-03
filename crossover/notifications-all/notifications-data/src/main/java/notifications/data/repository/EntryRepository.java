/**
 * 
 */
package notifications.data.repository;

import java.util.Date;
import java.util.List;

import notifications.data.domain.Entry;
import notifications.data.domain.helper.EntryStatus;
import notifications.data.domain.id.EntryID;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

/**
 * CRUD for Entries
 * @author luismr
 *
 */
public interface EntryRepository extends CrudRepository<Entry, EntryID> {

	@Query("select o from Entry o where o.status = :status and o.dateSchedule < :now")
	List<Entry> findAllBySyncAndDateSchedule(@Param("status") final EntryStatus status, @Param("now") final Date now);

	@Query("select o from Entry o where o.id.subscriberId = :subscriberID and o.id.categoryId = :categoryID")
	List<Entry> findAllBySubscription(@Param("subscriberID") final Long subscriberID, @Param("categoryID") final Integer categoryID);

	@Query("select o from Entry o where o.id.notificationId = :notificationID and o.status = :status and o.dateCreated < :date")
	List<Entry> findAllByNotificationIdAndSyncAndOlderThenDate(@Param("notificationID") final Long id, @Param("status") final EntryStatus status, @Param("date") final Date date);

}
