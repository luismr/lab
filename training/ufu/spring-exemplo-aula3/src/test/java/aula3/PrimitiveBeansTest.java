package aula3;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"/applicationContext.xml"})
public class PrimitiveBeansTest implements ApplicationContextAware {

	private ApplicationContext ctx;
	
	@Test
	public void stringTest() {
		String mensagem = (String) ctx.getBean("mensagem");
		
		assertNotNull(mensagem);
		assertTrue(mensagem.equals("Minha Mensagem"));
		
		System.out.println(mensagem);
	}
	
	@Test
	public void integerTest() {
		Integer numero = (Integer) ctx.getBean("numero");
		
		assertNotNull(numero);
		assertTrue(numero.equals(42));
		
		System.out.println(numero);
 	}
	
	@Test
	public void dateTest() {
		Date data = (Date) ctx.getBean("data");
		
		assertNotNull(data);
		assertTrue(System.currentTimeMillis() > data.getTime());
		
		System.out.println(data);
	}

	@Test
	public void propsTest() {
		Properties config = (Properties) ctx.getBean("config");
		
		assertNotNull(config);
		assertTrue(config.getProperty("communication").equals("async"));
		
		System.out.println(config);
	}
	
	@Test
	@SuppressWarnings("rawtypes")
	public void listTest() {
		List alunos = (List) ctx.getBean("alunos");
		
		assertNotNull(alunos);
		assertTrue(alunos.size() > 0);

		for (Iterator ite = alunos.iterator(); ite.hasNext(); ) {
			System.out.println(ite.next());
		}
	}
	
	@Test
	@SuppressWarnings("rawtypes")
	public void mapTest() {
		Map operadoras = (Map) ctx.getBean("operadoras");
		
		assertNotNull(operadoras);
		assertTrue(operadoras.containsValue("CTBC"));
		assertTrue(operadoras.containsKey("21"));
		
		for (Iterator ite = operadoras.keySet().iterator(); ite.hasNext();) {
			String key = (String) ite.next();
			String value = (String) operadoras.get(key);
			
			System.out.println("key = " + key + "   value = " + value);
		}
	}
	
	@Override
	public void setApplicationContext(ApplicationContext ctx) throws BeansException {
		this.ctx = ctx;
	}

}
