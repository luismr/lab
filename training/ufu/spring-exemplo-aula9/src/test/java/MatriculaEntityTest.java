import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import wifi.model.data.Matricula;
import wifi.model.data.MatriculaId;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"/applicationContext.xml"})
public class MatriculaEntityTest {

	@Autowired
	private SessionFactory sessionFactory;
	
	private Session session;
	
	@Before
	public void setup() throws Exception {
		session = sessionFactory.openSession();
	}

	@After
	public void shutdown() throws Exception {
		session.close();
	}

	@Test
	public void test() {
		MatriculaId id = new MatriculaId(1, 1);
		Matricula matricula = (Matricula) session.load(Matricula.class, id);
		
		System.out.println(matricula);
	}

}
