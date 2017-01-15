package org.secretaria.backend;

import java.util.ArrayList;
import java.util.List;

import org.secretaria.api.AlunoService;
import org.secretaria.data.Aluno;
import org.springframework.stereotype.Component;

@Component
public class FakeAlunoService implements AlunoService {

	public List<Aluno> listAll() {
		List<Aluno> alunos = new ArrayList<Aluno>();
		alunos.add(new Aluno(1, "luis"));
		alunos.add(new Aluno(2, "gabriel"));
		alunos.add(new Aluno(3, "rodrigo"));
		
		return alunos;
	}

}
