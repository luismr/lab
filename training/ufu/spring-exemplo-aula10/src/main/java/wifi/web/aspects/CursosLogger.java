package wifi.web.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;

@Aspect
public class CursosLogger {

	@AfterReturning("execution (* wifi.web.controller.CursosController.save(..))")
	public void afterCreate(JoinPoint p) {
		System.out.println("Curso salvo no banco de dados com sucesso!");
	}
}
