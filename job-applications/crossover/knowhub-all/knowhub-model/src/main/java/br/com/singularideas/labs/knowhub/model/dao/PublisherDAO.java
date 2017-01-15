package br.com.singularideas.labs.knowhub.model.dao;

import java.util.List;

import br.com.singularideas.labs.knowhub.common.data.Publisher;

public interface PublisherDAO {

    public Publisher save(Publisher p);
    public Publisher update(Publisher p);
    
    public void deleteById(long id);
    
    public Publisher getById(long id);
    public List<Publisher> getAll();
    public Publisher getByEmail(String email);
	
}
