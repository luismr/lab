package classic;

import static org.junit.Assert.assertTrue;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class MySQLJdbcTest {

	private Connection connection;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		Class.forName("com.mysql.jdbc.Driver");
	}

	@Before
	public void setUp() throws Exception {
		connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/simple","simple","simple");
	}

	@After
	public void tearDown() throws Exception {
		connection.close();
	}

	@Test
	public void test() throws SQLException {
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			stmt = connection.createStatement();
			
			rs = stmt.executeQuery("SELECT * FROM customers");
			
			while (rs.next()) {
				Integer id = rs.getInt("id");
				String name = rs.getString("nome");
				
				System.out.println(id + "\t - \t" + name);
			}
		} finally {
			if (rs != null) {
				rs.close();
			}
			
			if (stmt != null) {
				stmt.close();
			}
		}
		
		assertTrue(true);
	}

}
