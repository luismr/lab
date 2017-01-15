package br.com.singularideas.labs.knowhub.common.vo;

import java.util.Date;
import java.util.List;

import br.com.singularideas.labs.knowhub.common.data.Subscriber;
import br.com.singularideas.labs.knowhub.common.data.Subscription;

public interface Profile {

	Date getCreated();

	void setCreated(Date created);

	Subscriber getSubscriber();

	void setSubscriber(Subscriber subscriber);

	List<Subscription> getSubscriptions();

	void setSubscriptions(List<Subscription> subscriptions);

}