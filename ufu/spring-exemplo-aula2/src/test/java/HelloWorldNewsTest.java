import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import test.News;
import test.NewsService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"/applicationContext.xml"})
public class HelloWorldNewsTest {

	@Autowired
	private NewsService service;
	
	@Test
	public void test() {
		assertTrue(service.getTitle() == "Notícias Olá Mundo!");
		System.out.println(service.getTitle());
		System.out.println("---------------------------------");
		
		List<News> noticias = service.getNews();
		assertTrue(noticias != null);
		assertTrue(noticias.size() > 0);
		
		for (News noticia : noticias) {
			System.out.println(noticia);
		}
	}

}
