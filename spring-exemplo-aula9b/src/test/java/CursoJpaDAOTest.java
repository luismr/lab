import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import wifi.model.dao.DAO;
import wifi.model.data.Curso;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"/ctx-exemplo-aula9b.xml"})
public class CursoJpaDAOTest {

	@Autowired
	private DAO<Curso> cursoJpaDAO;

	@Test
	@Transactional
	public void listAllTest() {
		List<Curso> cursos = cursoJpaDAO.listAll();
		assertNotNull(cursos);
		assertTrue(cursos.size() > 0);
		
		System.out.println(cursos);
	}

}
