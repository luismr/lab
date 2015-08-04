package wifi.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import wifi.model.dao.DAO;
import wifi.model.data.Curso;

@Component
public class JpaCursoService implements CursoService {

	@Autowired
	private DAO<Curso> cursoJpaDAO;
	
	@Override
	@Transactional
	public List<Curso> getCursos() {
		return cursoJpaDAO.listAll();
	}

}
