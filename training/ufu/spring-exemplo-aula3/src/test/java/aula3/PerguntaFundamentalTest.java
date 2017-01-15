package aula3;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"/applicationContext.xml"})
public class PerguntaFundamentalTest implements ApplicationContextAware {

	private ApplicationContext ctx;
	
	@Autowired
	@Qualifier("pergunta3")
	private RespostaPerguntaFundamental pergunta;
	
	@Test
	public void test() {
		RespostaPerguntaFundamental resposta = (RespostaPerguntaFundamental) ctx.getBean("pergunta");
		
		assertNotNull(resposta);
		
		System.out.println(resposta);
	}
	
	@Test
	public void otherTest() {
		RespostaPerguntaFundamental resposta = (RespostaPerguntaFundamental) ctx.getBean("pergunta2");
		
		assertNotNull(resposta);
		
		System.out.println(resposta);		
	}

	@Test
	public void otherOtherTest() {
		RespostaPerguntaFundamental resposta = (RespostaPerguntaFundamental) ctx.getBean("pergunta3");
		
		assertNotNull(resposta);
		
		System.out.println(resposta);		
	}

	@Test
	public void calendarTest() throws ParseException {
		Date anonovo = (Date) ctx.getBean("anonovo");
		
		assertNotNull(anonovo);
		assertTrue(anonovo.equals((new Calendario("01/01/2014").createDate())));

		System.out.println(anonovo);
	}
	
	@Test
	public void otherCalendarTest() throws ParseException {
		Date anonovo = (Date) ctx.getBean("anonovo2");
		
		assertNotNull(anonovo);
		assertTrue(anonovo.equals((new Calendario("01/01/2014").createDate())));

		System.out.println(anonovo);
	}

	@Test
	public void otherOtherOtherTest() {
		assertNotNull(pergunta);
		System.out.println(pergunta);
	}

	@Override
	public void setApplicationContext(ApplicationContext ctx)
			throws BeansException {
		this.ctx = ctx;
	}

}
