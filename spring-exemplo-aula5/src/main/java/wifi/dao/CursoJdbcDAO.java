package wifi.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.sql.DataSource;

import wifi.data.Curso;

public class CursoJdbcDAO extends AbstractJdbcDAO implements CursoDAO {
	
	public CursoJdbcDAO(DataSource datasource) throws DAOException {
		super(datasource);
	}

	public CursoJdbcDAO(Properties params) throws DAOException {
		super(params);
	}

	@Override
	public void create(Curso c) throws DAOException {
		try {
			PreparedStatement stmt = 
					createPreparedStatement("INSERT INTO curso VALUES (?, ?)");
			stmt.setInt(1, c.getId());
			stmt.setString(2, c.getNome());
			
			stmt.execute();
		} catch (SQLException e) {
			throw new DAOException(e);
		}
	}

	@Override
	public Curso read(Curso c) throws DAOException {
		Curso curso = null;
		
		try {
			PreparedStatement stmt =
					createPreparedStatement("SELECT * FROM curso WHERE id = ?");
			stmt.setInt(1, c.getId());
			
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				curso = new Curso();
				curso.setId(rs.getInt("id"));
				curso.setNome(rs.getString("nome"));
			} 
		} catch (SQLException e) {
			throw new DAOException(e);
		}
		
		return curso;
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
