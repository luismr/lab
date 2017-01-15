package wifi.model.data;

import java.io.Serializable;

public class MatriculaId implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer cursoId;
	private Integer alunoId;
	
	public MatriculaId() {}
	
	public MatriculaId(final Integer cursoId, final Integer alunoId) {
		this.cursoId = cursoId;
		this.alunoId = alunoId;
	}

	public Integer getCursoId() {
		return cursoId;
	}

	public void setCursoId(Integer cursoId) {
		this.cursoId = cursoId;
	}

	public Integer getAlunoId() {
		return alunoId;
	}

	public void setAlunoId(Integer alunoId) {
		this.alunoId = alunoId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((alunoId == null) ? 0 : alunoId.hashCode());
		result = prime * result + ((cursoId == null) ? 0 : cursoId.hashCode());
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
		MatriculaId other = (MatriculaId) obj;
		if (alunoId == null) {
			if (other.alunoId != null)
				return false;
		} else if (!alunoId.equals(other.alunoId))
			return false;
		if (cursoId == null) {
			if (other.cursoId != null)
				return false;
		} else if (!cursoId.equals(other.cursoId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "MatriculaId [cursoId=" + cursoId + ", alunoId=" + alunoId + "]";
	}
	
	
	
}
