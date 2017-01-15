package wifi.dao;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;

import wifi.data.Curso;

public class CursoJdbcTemplateDAO extends AbstractJdbcTemplateDAO implements CursoDAO {

	public CursoJdbcTemplateDAO(JdbcTemplate jdbcTemplate) {
		super(jdbcTemplate);
	}

	@Override
	public void create(Curso t) {
		jdbcTemplate.update("INSERT INTO cursos VALUES (?, ?)", new Object [] {t.getId(), t.getNome()});
	}

	@Override
	public Curso read(Curso t) {
		return null;
	}

	@Override
	public Curso update(Curso t) {
		return null;
	}

	@Override
	public void delete(Curso t) {

	}

	@Override
	public List<Curso> listAll() {
		return null;
	}

}
