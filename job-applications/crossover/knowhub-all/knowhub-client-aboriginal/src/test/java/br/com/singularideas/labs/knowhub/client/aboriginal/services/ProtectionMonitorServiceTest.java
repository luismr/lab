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
public class ProtectionMonitorServiceTest {

	private static final String DEFAULT_MONITOR_WAIT = "10000";
	
	@Autowired
	private ProtectionMonitorService monitorService;
	
	@Test
	public void test() throws InterruptedException {
		try {
			assertNotNull(monitorService);
			
			String wait = System.getProperty("protection.monitor.wait", DEFAULT_MONITOR_WAIT);
			
			System.out.println("Waiting " + wait + " milisecs to proceed. Now you show test screen capture and clipboard ...");
			System.out.println("if you want change this delay, please informa -Dprotection.monitor.wait=new_timeour_in_millisecs");
			
			Thread.sleep(Long.parseLong(wait));
			
			System.out.println("Done!");
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

}
