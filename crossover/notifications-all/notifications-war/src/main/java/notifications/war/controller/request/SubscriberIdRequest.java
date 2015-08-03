/**
 * 
 */
package notifications.war.controller.request;

/**
 * @author luismr
 *
 */
public class SubscriberIdRequest {

	private Long subscriberId;
	
	public SubscriberIdRequest() {}
	
	public SubscriberIdRequest(final Long subscriberId) {
		this.subscriberId = subscriberId;
	}

	public Long getSubscriberId() {
		return subscriberId;
	}

	public void setSubscriberId(Long subscriberId) {
		this.subscriberId = subscriberId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
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
		SubscriberIdRequest other = (SubscriberIdRequest) obj;
		if (subscriberId == null) {
			if (other.subscriberId != null)
				return false;
		} else if (!subscriberId.equals(other.subscriberId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "SubscriberRequest [subscriberId=" + subscriberId + "]";
	}
	
}
