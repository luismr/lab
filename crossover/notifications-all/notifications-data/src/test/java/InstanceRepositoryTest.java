import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import notifications.data.NotificationDataConfig;
import notifications.data.domain.Instance;
import notifications.data.repository.InstanceRepository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = NotificationDataConfig.class)
public class InstanceRepositoryTest {

	@Autowired 
	InstanceRepository instanceRepository;
	
	@Test
	public void testFindAll() {
		Iterable<Instance> instances = instanceRepository.findAll();
		assertNotNull(instances);
		
		for (Instance instance : instances) {
			System.out.println(instance);
		}
		
	}
	
	@Test
	public void testCount() {
		long count = instanceRepository.count();
		assertTrue(count > 0);
		
		System.out.println(count);
	}

}
