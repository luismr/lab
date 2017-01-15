package br.com.singularideas.labs.knowhub.model.dao;

import java.util.List;

import br.com.singularideas.labs.knowhub.common.data.Channel;
import br.com.singularideas.labs.knowhub.common.data.Publisher;

public interface ChannelDAO {

    public Channel save(Channel p);
    public Channel update(Channel p);
    
    public void deleteById(long id);
    
    public Channel getById(long id);
    public List<Channel> getAll();
    public List<Channel> getByPublisher(Publisher p);
	
}
