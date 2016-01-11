package br.com.singularideas.labs.knowhub.model.dao;

import java.util.List;

import br.com.singularideas.labs.knowhub.common.data.Channel;
import br.com.singularideas.labs.knowhub.common.data.Item;

public interface ItemDAO {

    public Item save(Item i);
    public Item update(Item i);
    
    public void deleteById(long id);
    
    public Item getById(long id);
    public List<Item> getAll();
    public List<Item> getByChannel(Channel c);
	
}
