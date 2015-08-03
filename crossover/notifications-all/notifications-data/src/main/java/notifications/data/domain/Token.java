/**
 * 
 */
package notifications.data.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import notifications.data.domain.id.TokenId;

/**
 * Pojo for Tokens that allow access to 
 * another system publish their notifications
 *  
 * @author luismr
 *
 */
@Entity
@Table(name="tokens")
public class Token implements Serializable {

	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private TokenId id;
	
	@MapsId("id_instance")
	@JoinColumn(name = "id_instance", referencedColumnName = "id")
	@ManyToOne
	private Instance instance;
	
	@Column(name="name", nullable=false, length=45)
	private String name;
	
	@Column(name="password", nullable=false, length=20)
	private String password;
	
	@Column(name="password_hash", nullable=false, length=128)
	private String hash;
	
	public Token() {}

	public TokenId getId() {
		return id;
	}

	public void setId(TokenId id) {
		this.id = id;
	}
	
	public Instance getInstance() {
		return instance;
	}

	public void setInstance(Instance instance) {
		this.instance = instance;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getHash() {
		return hash;
	}

	public void setHash(String hash) {
		this.hash = hash;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((hash == null) ? 0 : hash.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((instance == null) ? 0 : instance.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result
				+ ((password == null) ? 0 : password.hashCode());
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
		Token other = (Token) obj;
		if (hash == null) {
			if (other.hash != null)
				return false;
		} else if (!hash.equals(other.hash))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (instance == null) {
			if (other.instance != null)
				return false;
		} else if (!instance.equals(other.instance))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Token [id=" + id + ", instance=" + instance + ", name=" + name
				+ ", password=" + password + ", hash=" + hash + "]";
	}
	
}