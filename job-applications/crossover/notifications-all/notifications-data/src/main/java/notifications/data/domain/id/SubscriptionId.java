/**
 * 
 */
package notifications.data.domain.id;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * Subscriptions PK
 * 
 * @author luismr
 *
 */
@Embeddable
public class SubscriptionId implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "id_subscriber")
	private Long subscriberId;
	
	@Column(name = "id_category")
	private Integer categoryId;
	
	public SubscriptionId() {}
	
	public SubscriptionId(final Long subscriberId, final Integer categoryId) {
		this.subscriberId = subscriberId;
		this.categoryId = categoryId;
	}

	public Long getSubscriberId() {
		return subscriberId;
	}

	public void setSubscriberId(Long subscriberId) {
		this.subscriberId = subscriberId;
	}

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((categoryId == null) ? 0 : categoryId.hashCode());
		result = prime * result
				+ ((subscriberId == null) ? 0 : subscriberId.hashCode());
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
		SubscriptionId other = (SubscriptionId) obj;
		if (categoryId == null) {
			if (other.categoryId != null)
				return false;
		} else if (!categoryId.equals(other.categoryId))
			return false;
		if (subscriberId == null) {
			if (other.subscriberId != null)
				return false;
		} else if (!subscriberId.equals(other.subscriberId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "SubscriptionId [subscriberId=" + subscriberId + ", categoryId="
				+ categoryId + "]";
	}
	
	
	
}
