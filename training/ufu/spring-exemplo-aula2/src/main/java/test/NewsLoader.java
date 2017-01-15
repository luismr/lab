package test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class NewsLoader {

	public static void main(String args[]) {
		
		ApplicationContext ctx = new ClassPathXmlApplicationContext("/applicationContext.xml");
		NewsService service = (NewsService) ctx.getBean("helloWorldNewsService");
		
		System.out.println(service.getTitle());
		System.out.println(service.getNews());
		
	}
	
}
