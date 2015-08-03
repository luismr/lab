/**
 * 
 */
package notifications.api;

/**
 * Subscription TO
 * 
 * @author luismr
 *
 */
public class SubscriptionTO {

	private Long customerId;
	private Integer categoryId;
	
	public SubscriptionTO() {}
	
	public SubscriptionTO(final Long customerId, final Integer categoryId) {
		this.customerId = customerId;
		this.categoryId = categoryId;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
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
				+ ((customerId == null) ? 0 : customerId.hashCode());
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
		SubscriptionTO other = (SubscriptionTO) obj;
		if (categoryId == null) {
			if (other.categoryId != null)
				return false;
		} else if (!categoryId.equals(other.categoryId))
			return false;
		if (customerId == null) {
			if (other.customerId != null)
				return false;
		} else if (!customerId.equals(other.customerId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "SubscriptionTO [customerId=" + customerId + ", categoryId="
				+ categoryId + "]";
	}
	
}
