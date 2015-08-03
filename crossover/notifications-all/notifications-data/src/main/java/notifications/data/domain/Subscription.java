/**
 * 
 */
package notifications.data.domain;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import notifications.data.domain.id.SubscriptionId;

/**
 * Subscription Categories for Subscriber
 * 
 * @author luismr
 *
 */
@Entity
@Table(name = "subscriptions")
public class Subscription implements Serializable {

	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private SubscriptionId id;
	
	@MapsId("id_category")
	@JoinColumn(name = "id_category", referencedColumnName = "id")
	@ManyToOne
	private Category category;

	@MapsId("id_subscriber")
	@JoinColumn(name = "id_subscriber", referencedColumnName = "id")
	@ManyToOne
	private Subscriber subscriber;

	public Subscription() {}

	public SubscriptionId getId() {
		return id;
	}

	public void setId(SubscriptionId id) {
		this.id = id;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Subscriber getSubscriber() {
		return subscriber;
	}

	public void setSubscriber(Subscriber subscriber) {
		this.subscriber = subscriber;
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((category == null) ? 0 : category.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((subscriber == null) ? 0 : subscriber.hashCode());
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
		Subscription other = (Subscription) obj;
		if (category == null) {
			if (other.category != null)
				return false;
		} else if (!category.equals(other.category))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (subscriber == null) {
			if (other.subscriber != null)
				return false;
		} else if (!subscriber.equals(other.subscriber))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Subscription [id=" + id + ", category=" + category + "]";
	}
	
}
