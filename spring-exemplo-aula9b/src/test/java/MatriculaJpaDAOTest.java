import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import javax.swing.text.MaskFormatter;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import wifi.model.dao.DAO;
import wifi.model.data.Matricula;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"/applicationContext.xml"})
public class MatriculaJpaDAOTest {

	@Autowired
	private DAO<Matricula> matriculaJpaDAO;
	
	@Test
	@Transactional(readOnly=true)
	public void listAllTest() {
		List<Matricula> matriculas = matriculaJpaDAO.listAll();
		assertNotNull(matriculas);
		assertTrue(matriculas.size() > 0);

//		for (Matricula m : matriculas) {
//			System.out.println(m.getAlunoId());
//			System.out.println(m.getCursoId());
//			System.out.println("----------------------");
//		}
		
		System.out.println(matriculas);
	}

}
