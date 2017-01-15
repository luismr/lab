package wifi.model.dao;

import java.util.List;

public interface DAO<T> {

	void create(T t);
	T read(T t);
	T update(T t);
	void delete(T t);
	List<T> listAll();	
	
}
