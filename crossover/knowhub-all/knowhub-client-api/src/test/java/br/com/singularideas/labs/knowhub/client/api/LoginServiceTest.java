package br.com.singularideas.labs.knowhub.client.api;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import br.com.singularideas.labs.knowhub.common.vo.Profile;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration( classes = {ApiConfig.class} )
public class LoginServiceTest {

	@Autowired
	private LoginService loginService;
	
	@Test
	public void testLogin() {
		Profile profile = loginService.login("luis.reis@singularideas.com.br", "test1234");
		assertNotNull(profile);
		
		System.out.println(profile);
	}

	@Test(expected=ApiException.class)
	public void testLoginInvalid() {
		Profile profile = loginService.login("luis.reis@singularideas.com.br", "test123");
		assertNotNull(profile);
		
		System.out.println(profile);
	}

}
