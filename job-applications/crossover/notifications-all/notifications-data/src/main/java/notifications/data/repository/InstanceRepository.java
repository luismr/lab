/**
 * 
 */
package notifications.data.repository;

import notifications.data.domain.Instance;

import org.springframework.data.repository.CrudRepository;

/**
 * CRUD for Instances
 * @author luismr
 *
 */
public interface InstanceRepository extends CrudRepository<Instance, Integer> {
}
