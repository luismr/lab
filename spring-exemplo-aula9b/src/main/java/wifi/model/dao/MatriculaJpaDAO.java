package wifi.model.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import wifi.model.data.Matricula;

@Repository
@Transactional
public class MatriculaJpaDAO implements DAO<Matricula> {

	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public void create(Matricula t) {
		manager.persist(t);
	}

	@Override
	public Matricula read(Matricula t) {
//		return manager.find(Matricula.class, t.getId());
		return null;
	}

	@Override
	public Matricula update(Matricula t) {
		return manager.merge(t);
	}

	@Override
	public void delete(Matricula t) {
		Matricula matricula = read(t);
		manager.remove(matricula);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Matricula> listAll() {
		return manager.createQuery("select m from Matricula m").getResultList();
	}

}
