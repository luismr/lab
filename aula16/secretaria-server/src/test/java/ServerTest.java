import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.secretaria.api.CursoService;
import org.secretaria.data.Curso;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"/ctx-secretaria-server.xml","/ctx-secretaria-backend.xml"})
public class ServerTest {

	@Autowired
	private CursoService cursoService;
	
	@Test
	public void test() {
		List<Curso> cursos = cursoService.listAll();
		assertNotNull(cursos);
		assertTrue(cursos.size() > 0);
	}

}
