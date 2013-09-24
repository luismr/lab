package wifi.model.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import wifi.model.data.Curso;

@Transactional
@Repository
public class CursoJpaDAO implements DAO<Curso> {
	
	@PersistenceContext
	EntityManager manager;
	
	@Override
	public void create(Curso t) {
		manager.persist(t);
	}

	@Override
	public Curso read(Curso t) {
		return manager.find(Curso.class, t.getId());
	}

	@Override
	public Curso update(Curso t) {
		return manager.merge(t);
	}

	@Override
	public void delete(Curso t) {
		Curso curso = read(t);
		manager.remove(curso);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Curso> listAll() {
		return manager.createQuery("select c from Curso c").getResultList();
	}

}
