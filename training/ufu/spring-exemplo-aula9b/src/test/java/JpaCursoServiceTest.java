import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import wifi.model.data.Curso;
import wifi.model.service.CursoService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"/ctx-exemplo-aula9b.xml"})
public class JpaCursoServiceTest {

	@Autowired
	private CursoService jpaCursoService;
	
	@Test
	@Transactional
	public void test() {
		List<Curso> cursos = jpaCursoService.getCursos();
		assertNotNull(cursos);
		assertTrue(cursos.size() > 0);
		
		System.out.println(cursos);
	}

}
