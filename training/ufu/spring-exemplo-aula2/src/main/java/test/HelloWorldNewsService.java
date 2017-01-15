package test;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class HelloWorldNewsService implements NewsService {

	private List<News> news;
	
	public HelloWorldNewsService() {
		news = new ArrayList<News>();
		news.add(new News("noticia1", "corpo da noticia 1"));
		news.add(new News("noticia2", "corpo da noticia 2"));
		news.add(new News("noticia4", "corpo da noticia 3"));
		news.add(new News("noticia3", "corpo da noticia 4"));
	}
	
	@Override
	public String getTitle() {
		return "Notícias Olá Mundo!";
	}

	@Override
	public List<News> getNews() {
		return news;
	}
}
