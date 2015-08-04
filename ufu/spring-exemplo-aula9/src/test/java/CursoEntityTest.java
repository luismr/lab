import static org.junit.Assert.*;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import wifi.model.data.Curso;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"/applicationContext.xml"})
public class CursoEntityTest {

	@Autowired
	private SessionFactory sessionFactory;
	
	private Session session;
	
	@Before
	public void setup() {
		session = sessionFactory.openSession();
	}
	
	@After
	public void shutdown() {
		session.close();
	}
	
//	@Test
	public void loadTest() {
		Curso c = (Curso) session.load(Curso.class, new Integer(1));
		assertNotNull(c);
		assertTrue(c.getId() == 1);
		
		System.out.println(c);
	}
	
	@Test
	public void getTest() {
		Curso c = (Curso) session.get(Curso.class, new Integer(1));
		assertNotNull(c);
		assertTrue(c.getId() == 1);

		System.out.println(c);
		
	}

}
