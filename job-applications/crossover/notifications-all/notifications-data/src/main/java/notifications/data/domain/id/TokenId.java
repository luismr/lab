/**
 * 
 */
package notifications.data.domain.id;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * Token PK
 * 
 * @author luismr
 *
 */
@Embeddable
public class TokenId implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Column(name = "id")
	private String tokenId;
	
	@Column(name = "id_instance")
	private Integer instanceId;

	public TokenId() {}
	
	public TokenId(final String tokenId, final Integer instanceId) {
		this.tokenId = tokenId;
		this.instanceId = instanceId;
	}

	public String getTokenId() {
		return tokenId;
	}

	public void setTokenId(String tokenId) {
		this.tokenId = tokenId;
	}

	public Integer getInstanceId() {
		return instanceId;
	}

	public void setInstanceId(Integer instanceId) {
		this.instanceId = instanceId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((instanceId == null) ? 0 : instanceId.hashCode());
		result = prime * result + ((tokenId == null) ? 0 : tokenId.hashCode());
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
		TokenId other = (TokenId) obj;
		if (instanceId == null) {
			if (other.instanceId != null)
				return false;
		} else if (!instanceId.equals(other.instanceId))
			return false;
		if (tokenId == null) {
			if (other.tokenId != null)
				return false;
		} else if (!tokenId.equals(other.tokenId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "TokenId [tokenId=" + tokenId + ", instanceId=" + instanceId
				+ "]";
	}
	
}
