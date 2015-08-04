import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import wifi.dao.DAOException;
import wifi.dao.MatriculaDAO;
import wifi.data.Aluno;
import wifi.data.Curso;
import wifi.data.Matricula;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"/applicationContext.xml"})
public class JdbcMatriculaDAO {

	@Autowired
	MatriculaDAO matriculaDAO;
	
	@Test
	public void createTest() throws DAOException {
		Curso c = new Curso(1, "Ciências da Computação");
		Aluno a = new Aluno(1, "Luis Machado Reis");
		
		Matricula m = new Matricula(a, c);
		
		matriculaDAO.create(m);
	}

}
