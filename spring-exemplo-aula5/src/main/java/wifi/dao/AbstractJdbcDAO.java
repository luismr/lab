package wifi.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public abstract class AbstractJdbcDAO {

	private Connection connection = null;
	private Properties databaseParams = null;
	
	public AbstractJdbcDAO(Properties params) throws DAOException {
		this.databaseParams = params;
		
		try {
			claimDatabaseDriver();
			setupConnection();
		} catch (Exception e) {
			throw new DAOException(e);
		}
	}

	protected Statement createStatement() throws SQLException {
		return connection.createStatement();
	}
	
	protected Statement createPreparedStatement(final String statement) throws SQLException {
		return connection.prepareCall(statement);
	}

	private void setupConnection() throws SQLException {
		String url = databaseParams.getProperty("database.url");
		String user = databaseParams.getProperty("database.user");
		String passwd = databaseParams.getProperty("database.passwd");
		
		connection = DriverManager.getConnection(url, user, passwd);
	}

	private void claimDatabaseDriver() throws ClassNotFoundException {
		String driver = databaseParams.getProperty("database.driver");
		Class.forName(driver);
	}
}
