package br.com.singularideas.labs.finance.utils.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import br.com.singularideas.labs.finance.utils.conf.UtilsConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration( classes = {UtilsConfig.class} )
public class JsonParserServiceTest {

	@Autowired
	private JsonParserService service;
	
	@Test
	public void test() {
		MyPojo p1 = new MyPojo();
		p1.setId("1");
		p1.setName("name");
		
		String encoded = service.encode(p1);
		assertNotNull(encoded);
		
		MyPojo p2 = service.decode(encoded, MyPojo.class);
		assertNotNull(p2);
		
		assertTrue(p1.getId().equals(p2.getId()));
		assertTrue(p1.getName().equals(p2.getName()));
	}

	private class MyPojo {
		private String id;
		private String name;
		
		public MyPojo() {}

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}
	}
	
}
