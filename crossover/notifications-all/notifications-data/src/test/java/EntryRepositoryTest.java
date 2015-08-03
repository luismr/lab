import static org.junit.Assert.assertNotNull;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import notifications.data.NotificationDataConfig;
import notifications.data.domain.Entry;
import notifications.data.domain.helper.EntryStatus;
import notifications.data.repository.EntryRepository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = NotificationDataConfig.class)
public class EntryRepositoryTest {

	@Autowired 
	EntryRepository entryRepository;
	
	@Test
	public void testFindAll() {
		Iterable<Entry> entries = entryRepository.findAll(); 
		assertNotNull(entries);
		
		for (Entry entry: entries) {
			System.out.println(entry);
		}
		
	}
	
	@Test
	public void testFindAllToDeliver() {
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setTime(new Date());
		calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		calendar.set(Calendar.HOUR_OF_DAY, 10);
		
		List<Entry> entries = entryRepository.findAllBySyncAndDateSchedule(EntryStatus.NEW, calendar.getTime());
		System.out.println(entries);
	}

}
