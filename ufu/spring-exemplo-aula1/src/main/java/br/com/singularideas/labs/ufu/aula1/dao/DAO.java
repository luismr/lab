/**
 * 
 */
package br.com.singularideas.labs.ufu.aula1.dao;

import java.util.List;

/**
 * @author luismr
 *
 */
public interface DAO<T> {

	void create(T t) throws DAOException;
	T read(Integer id) throws DAOException;
	T update(T t) throws DAOException;
	void delete(Integer id) throws DAOException;
	List<T> listAll();
	
}
