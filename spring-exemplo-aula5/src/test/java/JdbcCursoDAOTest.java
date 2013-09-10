import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import wifi.dao.CursoDAO;
import wifi.dao.DAOException;
import wifi.data.Curso;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"/applicationContext.xml"})
public class JdbcCursoDAOTest {

	@Autowired
	private CursoDAO cursoDAO;
	
	@Test
	public void test() throws DAOException {
		List<Curso> cursos = cursoDAO.listAll();
		assertNotNull(cursos);
		assertTrue(cursos.size() > 0);
		
		for (Curso curso : cursos) {
			System.out.println(curso);
		}
	}

}
