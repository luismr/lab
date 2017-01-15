package br.com.singularideas.labs.ufu.aula1;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import br.com.singularideas.labs.ufu.aula1.data.Cliente;
import br.com.singularideas.labs.ufu.aula1.service.ClienteService;
import br.com.singularideas.labs.ufu.aula1.service.ServiceException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"/applicationContext.xml"})
public class ClienteTest {

	@Autowired
	private ClienteService service;

	@Test
	public void createTest() throws ServiceException {
		Cliente c = service.create(1, "Luis Machado Reis", "luis.reis@singularideas.com.br");
		
		assertNotNull(c);
		assertNotNull(c.getId());
		assertTrue(c.getId().equals(1));
		
		System.out.println(c);
	}
	
	@Test
	public void readTest() throws ServiceException {
		Cliente c = service.create(2, "Leticia Carvalho", "leticia.carvalho@singularideas.com.br");
		
		c = service.read(2);
		
		assertNotNull(c);
		assertTrue(c.getId().equals(2));
		assertTrue(c.getNome().contains("Leticia"));
		
		System.out.println(c);
	}
	
	@Test
	public void updateTest() throws ServiceException {
		Cliente c = service.create(5, "Binho", "");
		c = service.read(5);
		
		assertNotNull(c);
		assertTrue(c.getId().equals(5));
		assertTrue(c.getNome().contains("Binho"));
		
		System.out.println(c);

		c.setNome("Binho e Kika");
		c = service.update(c);
		
		assertNotNull(c);
		assertTrue(c.getId().equals(5));
		assertTrue(c.getNome().contains("Kika"));

		System.out.println(c);
	}

	@Test
	public void listTest() throws ServiceException {
		Cliente c3 = service.create(3, "Mel", "");
		Cliente c4 = service.create(4, "Raika", "");
		
		List<Cliente> clientes = service.list();
		assertTrue(clientes != null);
		assertTrue(clientes.size() > 0);
		assertTrue(clientes.contains(c3));
		assertTrue(clientes.contains(c4));
		
		System.out.println(clientes);
	}

	@Test
	public void deleteTest() throws ServiceException {
		Cliente c6 = service.create(6, "Tony", "");
		Cliente c7 = service.create(7, "Gorda", "");
		
		List<Cliente> clientes = service.list();
		assertTrue(clientes != null);
		assertTrue(clientes.size() > 0);
		
		assertTrue(clientes.contains(c6));
		assertTrue(clientes.contains(c7));

		System.out.println(clientes);
		
		service.delete(c6.getId());

		assertFalse(clientes.contains(c6));
		assertTrue(clientes.contains(c7));

		System.out.println(clientes);
	}
	
}
