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
public class AppleScriptServiceTest {

	@Autowired
	private AppleScriptService script;
	
	@Autowired
	private RuntimeService runtime;
	
	@Test
	public void testExecute() {
		if (OperationalSystem.MAC.equals(runtime.getOperationalSystem())) {
			String out = script.execute("do shell script \"echo test\"");
			assertNotNull(out);
			assertTrue("test".equals(out));
		}
	}

}
