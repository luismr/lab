package wifi.model.service;

import java.rmi.Remote;
import java.util.List;

import wifi.model.data.Curso;

public interface RemoteCursoService extends Remote {

	List<Curso> getCursos();
	
}
