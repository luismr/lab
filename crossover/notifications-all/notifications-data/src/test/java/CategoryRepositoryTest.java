import static org.junit.Assert.assertNotNull;
import notifications.data.NotificationDataConfig;
import notifications.data.domain.Category;
import notifications.data.repository.CategoryRepository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = NotificationDataConfig.class)
public class CategoryRepositoryTest {

	@Autowired 
	CategoryRepository categoryRepository;
	
	@Test
	public void testFindAll() {
		Iterable<Category> categories = categoryRepository.findAll(); 
		assertNotNull(categories);
		
		for (Category category: categories) {
			System.out.println(category);
		}
		
	}
	

}
