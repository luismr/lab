/**
 * 
 */
package wifi.model.data;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


/**
 * @author luismr
 *
 */
@Entity
@Table(name="curso")
public class Curso {

	@Id
	@Column(name="id", nullable=false)
	@NotNull
	private Integer id;
	
	@Column(name="nome", nullable=false)
	@NotNull @Size(max=40)
	private String nome;
	
	@OneToMany(mappedBy="curso", 
			targetEntity=Matricula.class, 
			fetch=FetchType.LAZY, 
			cascade=CascadeType.ALL)
	private List<Matricula> matriculas;
	
	public Curso() {}
	
	public Curso(final Integer id, final String nome) {
		this.id = id;
		this.nome = nome;
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

	public List<Matricula> getMatriculas() {
		return matriculas;
	}

	public void setMatriculas(List<Matricula> matriculas) {
		this.matriculas = matriculas;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((matriculas == null) ? 0 : matriculas.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Curso other = (Curso) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (matriculas == null) {
			if (other.matriculas != null)
				return false;
		} else if (!matriculas.equals(other.matriculas))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Curso [id=" + id + ", nome=" + nome + ", matriculas="
				+ matriculas + "]";
	}

	
}
