/**
 * 
 */
package br.com.singularideas.labs.ufu.aula1.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.singularideas.labs.ufu.aula1.dao.ClienteDAO;
import br.com.singularideas.labs.ufu.aula1.dao.DAOException;
import br.com.singularideas.labs.ufu.aula1.data.Cliente;

/**
 * @author luismr
 *
 */
@Service
public class ClienteService {

	@Autowired
	private ClienteDAO clienteDAO;
	
	public ClienteService() {}
	
	public Cliente create(Integer id, String nome, String email) throws ServiceException {
		Cliente c = new Cliente(id, nome, email);
		
		try {
			clienteDAO.create(c);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		
		return c;
	}
	
	public Cliente read(Integer id) throws ServiceException {
		Cliente c = null;
		
		try {
			c = clienteDAO.read(id);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		
		return c;
	}
	
	public Cliente update(Cliente c) throws ServiceException {
		try {
			clienteDAO.update(c);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		
		return c;
	}
	
	public void delete(Integer id) throws ServiceException {
		try {
			clienteDAO.delete(id);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}
	
	public List<Cliente> list() {
		return clienteDAO.listAll();
	}
}
