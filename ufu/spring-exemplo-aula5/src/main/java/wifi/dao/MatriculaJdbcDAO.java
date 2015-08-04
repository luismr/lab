package wifi.dao;

import java.sql.PreparedStatement;
import java.sql.Types;
import java.util.List;
import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import wifi.data.Matricula;

public class MatriculaJdbcDAO extends AbstractJdbcDAO implements MatriculaDAO {

	public MatriculaJdbcDAO(DataSource datasource) {
		super(datasource);
	}

	public MatriculaJdbcDAO(DataSourceTransactionManager manager) {
		super(manager);
	}

	public MatriculaJdbcDAO(Properties params) {
		super(params);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void create(Matricula m) {
		try {
			PreparedStatement stmt = createPreparedStatement("INSERT INTO matricula VALUES (?, ?)");
			stmt.setInt(1, m.getCurso().getId());
			
			if (m.getAluno().getId() == null) {
				stmt.setNull(2, Types.INTEGER);
			} else {
				stmt.setInt(2, m.getAluno().getId());
			}
			
			stmt.execute();
		} catch (Exception e) {
			throw new DAOException(e);
		}
	}

	@Override
	public Matricula read(Matricula m) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Matricula update(Matricula m) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Matricula m) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Matricula> listAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
