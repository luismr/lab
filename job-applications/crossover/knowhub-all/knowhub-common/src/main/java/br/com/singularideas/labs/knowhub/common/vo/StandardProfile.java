package br.com.singularideas.labs.knowhub.common.vo;

import java.util.Date;
import java.util.List;

import br.com.singularideas.labs.knowhub.common.data.Subscriber;
import br.com.singularideas.labs.knowhub.common.data.Subscription;

public class StandardProfile implements Profile {

	private Date created;
	
	private Subscriber subscriber;
	
	private List<Subscription> subscriptions;
	
	public StandardProfile() {}

	/* (non-Javadoc)
	 * @see br.com.singularideas.labs.knowhub.common.vo.Profile#getCreated()
	 */
	@Override
	public Date getCreated() {
		return created;
	}

	/* (non-Javadoc)
	 * @see br.com.singularideas.labs.knowhub.common.vo.Profile#setCreated(java.util.Date)
	 */
	@Override
	public void setCreated(Date created) {
		this.created = created;
	}

	/* (non-Javadoc)
	 * @see br.com.singularideas.labs.knowhub.common.vo.Profile#getSubscriber()
	 */
	@Override
	public Subscriber getSubscriber() {
		return subscriber;
	}

	/* (non-Javadoc)
	 * @see br.com.singularideas.labs.knowhub.common.vo.Profile#setSubscriber(br.com.singularideas.labs.knowhub.common.data.Subscriber)
	 */
	@Override
	public void setSubscriber(Subscriber subscriber) {
		this.subscriber = subscriber;
	}

	/* (non-Javadoc)
	 * @see br.com.singularideas.labs.knowhub.common.vo.Profile#getSubscriptions()
	 */
	@Override
	public List<Subscription> getSubscriptions() {
		return subscriptions;
	}

	/* (non-Javadoc)
	 * @see br.com.singularideas.labs.knowhub.common.vo.Profile#setSubscriptions(java.util.List)
	 */
	@Override
	public void setSubscriptions(List<Subscription> subscriptions) {
		this.subscriptions = subscriptions;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((created == null) ? 0 : created.hashCode());
		result = prime * result + ((subscriber == null) ? 0 : subscriber.hashCode());
		result = prime * result + ((subscriptions == null) ? 0 : subscriptions.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		StandardProfile other = (StandardProfile) obj;
		if (created == null) {
			if (other.created != null)
				return false;
		} else if (!created.equals(other.created))
			return false;
		if (subscriber == null) {
			if (other.subscriber != null)
				return false;
		} else if (!subscriber.equals(other.subscriber))
			return false;
		if (subscriptions == null) {
			if (other.subscriptions != null)
				return false;
		} else if (!subscriptions.equals(other.subscriptions))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Session [created=" + created + ", subscriber=" + subscriber + ", subscriptions=" + subscriptions + "]";
	}
	
}
