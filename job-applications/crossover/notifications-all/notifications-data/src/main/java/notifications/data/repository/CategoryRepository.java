/**
 * 
 */
package notifications.data.repository;

import notifications.data.domain.Category;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

/**
 * CRUD for Categories
 * @author luismr
 *
 */
public interface CategoryRepository extends CrudRepository<Category, Integer> {

	@Cacheable("default")
	@Query("select o from Category o where o.instance.id = :instanceId")
	Iterable<Category> findAllByInstance(@Param("instanceId") Integer intanceId);
}
