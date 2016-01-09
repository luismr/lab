package br.com.singularideas.labs.knowhub.client.aboriginal.services;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import br.com.singularideas.labs.knowhub.client.aboriginal.AboriginalConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration( classes = {AboriginalConfig.class} )
public class RuntimeServiceTest {

	@Autowired
	private RuntimeService runtime;
	
	@Test
	public void testPerformString() {
		String out = runtime.perform("ls -d /tmp");
		assertNotNull(out);
		assertTrue("/tmp".equals(out));
	}

	@Test
	public void testPerformStringStringArray() {
		String out = runtime.perform("ls", new String [] { "-d", "/tmp"});
		assertNotNull(out);
		assertTrue("/tmp".equals(out));
	}

}
