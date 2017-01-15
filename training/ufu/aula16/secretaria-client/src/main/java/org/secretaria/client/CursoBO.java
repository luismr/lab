package org.secretaria.client;

import java.util.List;

import org.secretaria.api.CursoService;
import org.secretaria.data.Curso;

public class CursoBO {

	private CursoService cursoService;
	
	public CursoBO(final CursoService cursoService) {
		this.cursoService = cursoService;
	}
	
	public List<Curso> getCursos() {
		return cursoService.listAll();
	}
	
}
