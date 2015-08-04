package org.secretaria.backend;

import java.util.ArrayList;
import java.util.List;

import org.secretaria.api.CursoService;
import org.secretaria.data.Curso;
import org.springframework.stereotype.Component;

@Component
public class FakeCursoService implements CursoService {

	public List<Curso> listAll() {
		List<Curso> cursos = new ArrayList<Curso>();
		cursos.add(new Curso(1, "Computação"));
		cursos.add(new Curso(2, "Nutrição"));
		cursos.add(new Curso(3, "Psicologia"));
		
		return cursos;
	}

}
