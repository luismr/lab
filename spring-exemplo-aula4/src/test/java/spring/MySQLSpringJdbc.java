package spring;

import static org.junit.Assert.*;

import java.util.List;

import javax.sql.DataSource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import data.Cliente;
import data.ClienteRowMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"/applicationContext.xml"})
public class MySQLSpringJdbc {

	@Autowired
	private DataSource dataSource;
	
	private JdbcTemplate jdbcTemplate;
	
	@Before
	public void setUp() throws Exception {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	@Test
	public void selectTest() {
		List<Cliente> clientes = jdbcTemplate.query("SELECT * FROM customers", new ClienteRowMapper());
		
		for (Cliente cliente : clientes) {
			System.out.println(cliente);
		}
		
		assertNotNull(clientes);
		assertTrue(clientes.size() > 0);
	}
	
	@Test
	public void insertTest() {
		Cliente cliente = new Cliente(4, "Ana Caroline");
		int rows = jdbcTemplate.update("INSERT INTO customers VALUES (?, ?)", new Object[] {cliente.getId(), cliente.getNome()});
		
		assertTrue(rows > 0);
	}
	
}
