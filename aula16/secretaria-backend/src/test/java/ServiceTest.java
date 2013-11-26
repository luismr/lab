import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.secretaria.api.AlunoService;
import org.secretaria.api.CursoService;
import org.secretaria.api.MatriculaService;
import org.secretaria.data.Aluno;
import org.secretaria.data.Curso;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"/ctx-secretaria-backend.xml"})
public class ServiceTest {

	@Autowired
	private AlunoService alunoService;
	
	@Autowired
	private CursoService cursoService;
	
	@Autowired
	private MatriculaService matriculaService;
	
	@Test
	public void testAlunoService() {
		List<Aluno> alunos = alunoService.listAll();
		assertNotNull(alunos);
		
		for (Aluno aluno : alunos) {
			System.out.println(aluno);
		}
	}
	
	@Test
	public void testCursoService() {
		List<Curso> cursos = cursoService.listAll();
		assertNotNull(cursos);
		
		for (Curso curso : cursos) {
			System.out.println(curso);
		}
	}
	
	@Test
	public void testMatriculaService() {
		Aluno aluno = new Aluno(1, "Gabriel");
		Curso curso = new Curso(1, "Nutrição");
		
		Integer matriculaId = matriculaService.matricular(curso, aluno);
		assertNotNull(matriculaId);
		
		System.out.println(matriculaId);
	}

}
