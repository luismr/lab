package wifi.data;

public class Matricula {

	private Aluno aluno;
	private Curso curso;
	
	public Matricula() {}
	
	public Matricula(final Aluno aluno, final Curso curso) {
		this.aluno = aluno;
		this.curso = curso;
	}

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

	public Curso getCurso() {
		return curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}
}
