package wifi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import wifi.dao.CursoDAO;
import wifi.dao.DAOException;
import wifi.dao.MatriculaDAO;
import wifi.data.Curso;
import wifi.data.Matricula;

public class AcademicoService {

	@Autowired
	private CursoDAO cursoDAO;
	
	@Autowired
	private MatriculaDAO matriculaDAO;

	@Transactional(propagation=Propagation.REQUIRED)
	public Matricula createMatriculaAndCursoAndOrAluno(final Matricula m) throws ServiceException, DAOException {
		if (m == null) {
			throw new ServiceException("m == null");
		} else if (m.getAluno() == null) {
			throw new ServiceException("m.aluno == null");
		} else if (m.getCurso() == null) {
			throw new ServiceException("m.curso == null");
		}

		if (cursoDAO.read(m.getCurso()) == null) {
			cursoDAO.create(m.getCurso());
		}
		
		//FIXME Pelo amor de Deus implementar o AlunoDAO
//		if (!true) {
//			//alunoDAO.create(m.getAluno());
//		}
		
		matriculaDAO.create(m);
		
		return m;
	}
	
	@Transactional(propagation=Propagation.REQUIRED)
	public Curso createCurso(final Curso c) {
		cursoDAO.create(c);
		return c;
	}
	
	@Transactional(propagation=Propagation.REQUIRED)
	public Matricula createMatricula(final Matricula m) throws ServiceException {
		if (m == null) {
			throw new ServiceException("m == null");
		} else if (m.getAluno() == null) {
			throw new ServiceException("m.aluno == null");
		} else if (m.getCurso() == null) {
			throw new ServiceException("m.curso == null");
		}
		
		try {
			if (cursoDAO.read(m.getCurso()) == null) {
				throw new ServiceException("m.curso não existe!");
			}
			
			//FIXME Pelo amor de Deus implementar o AlunoDAO
//			if (!true) {
//				throw new ServiceException("m.aluno não existe!");
//			}
			
			matriculaDAO.create(m);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		
		return m;
	}
	
}
