package org.secretaria.backend;

import java.math.BigDecimal;

import org.secretaria.api.MatriculaService;
import org.secretaria.data.Aluno;
import org.secretaria.data.Curso;
import org.springframework.stereotype.Component;

@Component
public class FakeMatriculaService implements MatriculaService {

	public Integer matricular(final Curso curso, final Aluno aluno) {
		Double d = Math.random();
		d = d * 100;
		
		return (new BigDecimal(d)).intValue();
	}

}
