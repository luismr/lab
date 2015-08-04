/**
 * 
 */
package br.com.singularideas.labs.ufu.aula1.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import br.com.singularideas.labs.ufu.aula1.data.Cliente;

/**
 * @author luismr
 *
 */
@Component
public class MemoryClienteDAO implements ClienteDAO {

	private List<Cliente> clientes;
	
	public MemoryClienteDAO() {
		this.clientes = new ArrayList<Cliente>();
	}
	
	/* (non-Javadoc)
	 * @see br.com.singularideas.labs.ufu.aula1.dao.DAO#create(java.lang.Object)
	 */
	@Override
	public void create(Cliente c) throws DAOException {
		if (c == null || c.getId() == null) {
			throw new DAOException("c== null || id == null");
		} else if (read(c.getId()) != null) {
			throw new DAOException("id [" + c.getId() + "] already exists");
		}
		
		clientes.add(c);
	}

	/* (non-Javadoc)
	 * @see br.com.singularideas.labs.ufu.aula1.dao.DAO#read(java.lang.Integer)
	 */
	@Override
	public Cliente read(Integer id) throws DAOException {
		if (id == null) {
			throw new DAOException("id == null");
		}
		
		for (Cliente cliente : clientes) {
			if (cliente != null && cliente.getId() != null && cliente.getId().equals(id)) {
				return cliente;
			}
		}
		
		return null;
	}

	/* (non-Javadoc)
	 * @see br.com.singularideas.labs.ufu.aula1.dao.DAO#update(java.lang.Object)
	 */
	@Override
	public Cliente update(Cliente c) throws DAOException {
		if (c == null || c.getId() == null) {
			throw new DAOException("c == null || id == null");
		} 
		
		delete(c.getId());
		create(c);
		
		return c;
	}

	/* (non-Javadoc)
	 * @see br.com.singularideas.labs.ufu.aula1.dao.DAO#delete(java.lang.Integer)
	 */
	@Override
	public void delete(Integer id) throws DAOException {
		if (id == null) {
			throw new DAOException("id == null");
		} 
		
		Cliente c = read(id);
		
		if (c == null) {
			throw new DAOException("id [" + id + "] does not exists");
		}
		
		if (!clientes.remove(c)) {
			throw new DAOException("cannot remove id[" + id + "]");
		}
	}

	/* (non-Javadoc)
	 * @see br.com.singularideas.labs.ufu.aula1.dao.DAO#listAll()
	 */
	@Override
	public List<Cliente> listAll() {
		return clientes;
	}

}
