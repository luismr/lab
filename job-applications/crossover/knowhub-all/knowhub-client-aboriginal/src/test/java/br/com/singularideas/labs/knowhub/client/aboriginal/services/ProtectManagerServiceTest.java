package br.com.singularideas.labs.knowhub.client.aboriginal.services;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import br.com.singularideas.labs.knowhub.client.aboriginal.AboriginalConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration( classes = {AboriginalConfig.class} )
public class ProtectManagerServiceTest {

	@Autowired
	private ProtectManagerService protectManagerService;
	
	@Test
	public void testGetManager() {
		ProtectManager pm = protectManagerService.getManager();
		assertNotNull(pm);
	}

}
