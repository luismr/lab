package wifi.data;

public class Aluno {
	
	private Integer id;
	private String nome;
	private String email;
	
	public Aluno() {}
	
	public Aluno(final Integer id, final String nome) {
		this(id, nome, null);
	}
	
	public Aluno(final Integer id, final String nome, final String email) {
		this.id = id;
		this.nome = nome;
		this.email = email;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
