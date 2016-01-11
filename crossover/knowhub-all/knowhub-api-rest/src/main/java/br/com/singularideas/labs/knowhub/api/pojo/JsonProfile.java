package br.com.singularideas.labs.knowhub.api.pojo;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import br.com.singularideas.labs.knowhub.api.serializer.JsonDateSerializer;
import br.com.singularideas.labs.knowhub.common.data.Subscriber;
import br.com.singularideas.labs.knowhub.common.data.Subscription;
import br.com.singularideas.labs.knowhub.common.vo.Profile;

public class JsonProfile implements Profile {

	private Date created;
	
	private Subscriber subscriber;
	
	private List<Subscription> subscriptions;
	
	public JsonProfile(final Profile profile) {
		this.created = profile.getCreated();
		this.subscriber = profile.getSubscriber();
		this.subscriptions = profile.getSubscriptions();
	}

	@Override
	@JsonSerialize(using=JsonDateSerializer.class)
	public Date getCreated() {
		return created;
	}

	@Override
	public void setCreated(Date created) {
		this.created = created;
	}

	@Override
	public Subscriber getSubscriber() {
		return subscriber;
	}

	@Override
	public void setSubscriber(Subscriber subscriber) {
		this.subscriber = subscriber;
	}

	@Override
	public List<Subscription> getSubscriptions() {
		return subscriptions;
	}

	@Override
	public void setSubscriptions(List<Subscription> subscriptions) {
		this.subscriptions = subscriptions;
	}

}
