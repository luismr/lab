package org.secretaria.server.remote;

import java.rmi.Remote;
import java.util.List;

import org.secretaria.data.Curso;

public interface RemoteCursoService extends Remote {

	List<Curso> listAll();
	
}
