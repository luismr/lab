package wifi.dao;

import java.util.List;

public interface DAO<T> {

	void create(T t) throws DAOException;
	T read(T t) throws DAOException;
	T update(T t) throws DAOException;
	void delete(T t) throws DAOException;
	List<T> listAll() throws DAOException;

}
