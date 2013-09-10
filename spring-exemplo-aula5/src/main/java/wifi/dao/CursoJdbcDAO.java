package wifi.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import wifi.data.Curso;

public class CursoJdbcDAO extends AbstractJdbcDAO implements CursoDAO {

	public CursoJdbcDAO(Properties params) throws DAOException {
		super(params);
	}

	@Override
	public void create(Curso c) throws DAOException {

	}

	@Override
	public Curso read(Curso c) throws DAOException {
		return null;
	}

	@Override
	public Curso update(Curso c) throws DAOException {
		return null;
	}

	@Override
	public void delete(Curso c) throws DAOException {

	}

	@Override
	public List<Curso> listAll() throws DAOException {
		List<Curso> cursos = new ArrayList<Curso>();
		
		try {
			ResultSet rs = createStatement().executeQuery("SELECT * FROM curso");
			while (rs.next()) {
				Curso c = new Curso();
				c.setId(rs.getInt("id"));
				c.setNome(rs.getString("nome"));
				
				cursos.add(c);
			}
		} catch (SQLException e) {
			throw new DAOException(e);
		}
		
		return cursos;
	}

}
