package br.com.singularideas.labs.knowhub.model.dao;

import java.util.List;

import br.com.singularideas.labs.knowhub.common.data.Subscriber;
import br.com.singularideas.labs.knowhub.common.data.Subscription;

public interface SubscriptionDAO {

    public Subscription save(Subscription s);
    public Subscription update(Subscription s);
    
    public void deleteById(long id);
    
    public Subscription getById(long id);
    public List<Subscription> getAll();
    public List<Subscription> getBySubscriber(Subscriber s);
	
}
