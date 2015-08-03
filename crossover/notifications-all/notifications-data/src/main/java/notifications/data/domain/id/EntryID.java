package notifications.data.domain.id;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * Entry PK
 * 
 * @author luismr
 *
 */
@Embeddable
public class EntryID implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "id_subscriber")
	private Long subscriberId;
	
	@Column(name = "id_category")
	private Integer categoryId;
	
	@Column(name = "id_notification")
	private Long notificationId;

	public EntryID() {}
	
	public EntryID(final SubscriptionId subscriptionId, final Long notificationId) throws Exception {
		if (subscriptionId == null) {
			throw new Exception("subscriptionId == null");
		}
		
		this.subscriberId = subscriptionId.getSubscriberId();
		this.categoryId = subscriptionId.getCategoryId();
		this.notificationId = notificationId;
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

	public Long getNotificationId() {
		return notificationId;
	}

	public void setNotificationId(Long notificationId) {
		this.notificationId = notificationId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((categoryId == null) ? 0 : categoryId.hashCode());
		result = prime * result
				+ ((notificationId == null) ? 0 : notificationId.hashCode());
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
		EntryID other = (EntryID) obj;
		if (categoryId == null) {
			if (other.categoryId != null)
				return false;
		} else if (!categoryId.equals(other.categoryId))
			return false;
		if (notificationId == null) {
			if (other.notificationId != null)
				return false;
		} else if (!notificationId.equals(other.notificationId))
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
		return "EntryID [subscriberId=" + subscriberId + ", categoryId="
				+ categoryId + ", notificationId=" + notificationId + "]";
	}
	
}
