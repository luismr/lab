package br.com.singularideas.labs.knowhub.model.dao;

import java.util.List;

import br.com.singularideas.labs.knowhub.common.data.Subscriber;

public interface SubscriberDAO {

    public Subscriber save(Subscriber s);
    public Subscriber update(Subscriber s);
    
    public void deleteById(long id);
    
    public Subscriber getById(long id);
    public List<Subscriber> getAll();
    public Subscriber getByEmail(String email);
	
}
