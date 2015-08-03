package notifications.core.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import notifications.api.CategoryTO;
import notifications.api.TokenTO;
import notifications.core.NotificationCoreException;
import notifications.core.util.EntityTOTransformUtils;
import notifications.data.domain.Category;
import notifications.data.domain.Instance;
import notifications.data.repository.CategoryRepository;
import notifications.data.repository.InstanceRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Categories Service
 * 
 * @author luismr
 *
 */
@Service
public class CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private InstanceRepository instanceRepository;
	
	/**
	 * List All Categories
	 * @param token 
	 * @return List of Categories in TO
	 */
	public List<CategoryTO> listCategories(TokenTO token) {
		List<CategoryTO> categories = new ArrayList<CategoryTO>();
		
		Iterable<Category> icategories = categoryRepository.findAllByInstance(token.getInstanceId());
		for (Category category : icategories) {
			categories.add(EntityTOTransformUtils.transformFromCategory(category));
		}
		
		return categories;
	}
	
	/**
	 * Read a Category
	 * @param id
	 * @return
	 */
	public CategoryTO read(final Integer id) {
		Category category = categoryRepository.findOne(id);
		if (category == null) {
			throw new NotificationCoreException("category ID [" + id + "] not found!");
		}
		
		return EntityTOTransformUtils.transformFromCategory(category);
	}

	/**
	 * Create a New Category
	 * @param categoryTO
	 * @return
	 */
	@Transactional(TxType.REQUIRES_NEW)
	public CategoryTO create(CategoryTO categoryTO) {
		Category category = EntityTOTransformUtils.transformFromCategoryTO(categoryTO);
		
		Instance instance = instanceRepository.findOne(categoryTO.getInstanceId());
		category.setInstance(instance);
		
		category = categoryRepository.save(category);
		return EntityTOTransformUtils.transformFromCategory(category);
	}

	/**
	 * Update a Category
	 * @param categoryTO
	 * @return
	 */
	@Transactional
	public CategoryTO update(CategoryTO categoryTO) {
		Category category = categoryRepository.findOne(categoryTO.getId());
		category.setName(categoryTO.getName());
		category.setDescription(categoryTO.getDescription());

		if (category.getInstance().getId().equals(categoryTO.getInstanceId()) == false) {
			Instance instance = new Instance();
			instance.setId(categoryTO.getInstanceId());
			category.setInstance(instance);
		}
		
		category = categoryRepository.save(category);
		return EntityTOTransformUtils.transformFromCategory(category);
	}
	
	/**
	 * Delete a Category
	 */
	@Transactional
	public void delete(Integer categoryId) {
		categoryRepository.delete(categoryId);
	}
	
}
