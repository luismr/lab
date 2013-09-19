package wifi.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.jdbc.datasource.DataSourceTransactionManager;

public abstract class AbstractJdbcDAO {

	private Connection connection = null;
	private DataSource datasource = null;
	private DataSourceTransactionManager manager = null;
	private Properties databaseParams = null;
	
	public AbstractJdbcDAO(Properties params) {
		this.databaseParams = params;
		
		try {
			claimDatabaseDriver();
			setupConnection();
		} catch (Exception e) {
			throw new DAOException(e);
		}
	}

	public AbstractJdbcDAO(DataSource datasource) {
		this.datasource = datasource;

		try {
			setupConnection();
		} catch (Exception e) {
			throw new DAOException(e);
		}
	}
	
	public AbstractJdbcDAO(DataSourceTransactionManager manager) {
		this.manager = manager;
		
		try {
			setupConnection();
		} catch (Exception e) {
			throw new DAOException(e);
		}
	}
	
	protected Statement createStatement() throws SQLException {
		return connection.createStatement();
	}
	
	protected PreparedStatement createPreparedStatement(final String statement) throws SQLException {
		return connection.prepareCall(statement);
	}

	private void setupConnection() throws SQLException {
		if (manager != null) {
			datasource = manager.getDataSource();
			connection = datasource.getConnection();
		} else if (datasource != null) {
			connection = datasource.getConnection();
		} else {
			String url = databaseParams.getProperty("database.url");
			String user = databaseParams.getProperty("database.user");
			String passwd = databaseParams.getProperty("database.passwd");
			
			connection = DriverManager.getConnection(url, user, passwd);
		}
	}

	private void claimDatabaseDriver() throws ClassNotFoundException {
		String driver = databaseParams.getProperty("database.driver");
		Class.forName(driver);
	}
}
