import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.secretaria.client.CursoBO;
import org.secretaria.data.Curso;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"/ctx-secretaria-client.xml"})
public class CursoTest {

	@Autowired
	private CursoBO bo;
	
	@Test
	public void testListAll() {
		List<Curso> cursos = bo.getCursos();
		assertNotNull(cursos);
		assertTrue(cursos.size() > 0);
		
		for (Curso curso : cursos) {
			System.out.println(curso);
		}
	}

}
