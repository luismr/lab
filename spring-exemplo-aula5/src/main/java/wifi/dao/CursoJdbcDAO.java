package wifi.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import wifi.data.Curso;

public class CursoJdbcDAO extends AbstractJdbcDAO implements CursoDAO {
	
	public CursoJdbcDAO(DataSource datasource) {
		super(datasource);
	}

	public CursoJdbcDAO(DataSourceTransactionManager manager) {
		super(manager);
	}

	public CursoJdbcDAO(Properties params) {
		super(params);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void create(Curso c) {
		try {
			PreparedStatement stmt = 
					createPreparedStatement("INSERT INTO curso VALUES (?, ?)");
			stmt.setInt(1, c.getId());
			stmt.setString(2, c.getNome());
			
			stmt.execute();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=true)
	public Curso read(Curso c) {
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
			throw new RuntimeException(e);
		}
		
		return curso;
	}

	@Override
	public Curso update(Curso c) {
		return null;
	}

	@Override
	public void delete(Curso c) {

	}

	@Override
	public List<Curso> listAll() {
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
			throw new RuntimeException(e);
		}
		
		return cursos;
	}

}
