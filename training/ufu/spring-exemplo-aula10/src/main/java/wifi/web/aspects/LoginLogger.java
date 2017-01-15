package wifi.web.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Aspect
public class LoginLogger {

	@Before("execution (* wifi.web.controller.LoginController.login())")
	public void beforeLogin(JoinPoint p) {
		System.out.println("Tela de Login acessada!");
	}
	
	@After("execution (* wifi.web.controller.LoginController.login())")
	public void afterLogin(JoinPoint p) {
		System.out.println("Login Efetuado!");
	}
	
	@After("execution (* wifi.web.controller.LoginController.logout())")
	public void afterLogout(JoinPoint p) {
		System.out.println("Logout Efetuado!");
	}
	
	@After("execution (* wifi.web.controller.LoginController.denied())")
	public void afterDenied(JoinPoint p) {
		System.out.println("Usuario/Senha inválidos");
	}

}
