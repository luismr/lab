package wifi.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;
import java.util.Properties;

import javax.sql.DataSource;

import wifi.data.Matricula;

public class MatriculaJdbcDAO extends AbstractJdbcDAO implements MatriculaDAO {

	public MatriculaJdbcDAO(DataSource datasource) throws DAOException {
		super(datasource);
	}

	public MatriculaJdbcDAO(Properties params) throws DAOException {
		super(params);
	}

	@Override
	public void create(Matricula m) throws DAOException {
		try {
			PreparedStatement stmt = createPreparedStatement("INSERT INTO matricula VALUES (?, ?)");
			stmt.setInt(1, m.getCurso().getId());
			
			if (m.getAluno().getId() == null) {
				stmt.setNull(2, Types.INTEGER);
			} else {
				stmt.setInt(2, m.getAluno().getId());
			}
			
			stmt.execute();
		} catch (SQLException e) {
			throw new DAOException(e);
		}
	}

	@Override
	public Matricula read(Matricula m) throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Matricula update(Matricula m) throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Matricula m) throws DAOException {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Matricula> listAll() throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

}
