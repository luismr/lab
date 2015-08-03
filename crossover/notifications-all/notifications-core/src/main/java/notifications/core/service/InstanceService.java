package notifications.core.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import notifications.api.InstanceTO;
import notifications.core.NotificationCoreException;
import notifications.core.util.EntityTOTransformUtils;
import notifications.data.domain.Instance;
import notifications.data.repository.InstanceRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Instances Service
 * 
 * @author luismr
 *
 */
@Service
public class InstanceService {

	@Autowired
	private InstanceRepository instanceRepository;
	
	/**
	 * List All Instances
	 * @return List of Categories in TO
	 */
	public List<InstanceTO> listInstances() {
		List<InstanceTO> instances = new ArrayList<InstanceTO>();
		
		Iterable<Instance> iinstances= instanceRepository.findAll();
		for (Instance instance : iinstances) {
			instances.add(EntityTOTransformUtils.transformFromInstance(instance));
		}
		
		return instances;
	}
	
	/**
	 * Read an Instance
	 * @param id
	 * @return
	 */
	public InstanceTO read(final Integer id) {
		Instance instance = instanceRepository.findOne(id);
		if (instance == null) {
			throw new NotificationCoreException("instance ID [" + id + "] not found!");
		}
		
		return EntityTOTransformUtils.transformFromInstance(instance);
	}

	/**
	 * Create a New Instance
	 * @param instanceTO
	 * @return
	 */
	@Transactional(TxType.REQUIRES_NEW)
	public InstanceTO create(InstanceTO instanceTO) {
		Instance instance = EntityTOTransformUtils.transformFromInstanceTO(instanceTO);
		instance = instanceRepository.save(instance);
		
		return EntityTOTransformUtils.transformFromInstance(instance);
	}

	/**
	 * Update an Instance
	 * @param instanceTO
	 * @return
	 */
	@Transactional
	public InstanceTO update(InstanceTO instanceTO) {
		Instance instance = instanceRepository.findOne(instanceTO.getId());
		instance.setName(instanceTO.getName());
		instance.setDescription(instanceTO.getDescription());
		
		instance = instanceRepository.save(instance);
		return EntityTOTransformUtils.transformFromInstance(instance);
	}
	
	/**
	 * Delete an Instance
	 */
	@Transactional
	public void delete(Integer instanceId) {
		instanceRepository.delete(instanceId);
	}
	
}
