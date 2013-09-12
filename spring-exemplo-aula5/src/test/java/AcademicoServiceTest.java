import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import wifi.data.Aluno;
import wifi.data.Curso;
import wifi.data.Matricula;
import wifi.service.AcademicoService;
import wifi.service.ServiceException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"/applicationContext.xml"})
public class AcademicoServiceTest {

	@Autowired
	private AcademicoService academicoService;
	
	@Test
	public void createMatriculaTest() throws ServiceException {
		Curso c = new Curso(1, "Ciências da Computação");
		Aluno a = new Aluno(1, "Luis Machado Reis");
		
		Matricula m = new Matricula(a, c);

		Matricula matricula = academicoService.createMatricula(m);
		assertNotNull(matricula);
	}

	@Test
	public void createMatriculaWithPalaTest() throws ServiceException {
		Curso c = new Curso(3, "Nutrição");
		Aluno a = new Aluno(null, "Gabriel Coutinho");

		Matricula m = new Matricula(a, c);
		
		Matricula matricula = academicoService.createMatriculaAndCursoAndOrAluno(m);
		assertNotNull(matricula);
	}
}
