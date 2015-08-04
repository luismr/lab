package org.secretaria.api;

import org.secretaria.data.Aluno;
import org.secretaria.data.Curso;

public interface MatriculaService {

	Integer matricular(Curso curso, Aluno aluno);
	
}
