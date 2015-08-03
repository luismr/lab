/**
 * 
 */
package notifications.data.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import notifications.data.domain.helper.Affirmative;
import notifications.data.domain.helper.Frequency;

/**
 * Subscribers Entity
 * 
 * @author luismr
 *
 */
@Entity
@Table(name = "subscribers")
public class Subscriber implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@MapsId("id_instance")
	@JoinColumn(name = "id_instance", referencedColumnName = "id")
	@ManyToOne
	private Instance instance;

	@Column(name = "name", nullable = false)
	private String name;
	
	@Column(name = "email", nullable = false)
	private String email;
	
	@Column(name = "pin", nullable = false)
	private String pin;
	
	@Column(name = "pin_hash", nullable = false)
	private String hash;
	
	@Column(name = "phone")
	private String phone;
	
	@Column(name = "notify_phone", nullable = false)
	@Enumerated(EnumType.STRING)
	private Affirmative notifyByPhone;
	
	@Column(name = "frequency", nullable = false)
	@Enumerated(EnumType.STRING)
	private Frequency frequency;
	
	@OneToMany(mappedBy = "subscriber", fetch = FetchType.LAZY)
	private List<Subscription> subscriptions;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPin() {
		return pin;
	}

	public void setPin(String pin) {
		this.pin = pin;
	}

	public String getHash() {
		return hash;
	}

	public void setHash(String hash) {
		this.hash = hash;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Affirmative getNotifyByPhone() {
		return notifyByPhone;
	}

	public void setNotifyByPhone(Affirmative notifyByPhone) {
		this.notifyByPhone = notifyByPhone;
	}

	public Frequency getFrequency() {
		return frequency;
	}

	public void setFrequency(Frequency frequency) {
		this.frequency = frequency;
	}

	public List<Subscription> getSubscriptions() {
		return subscriptions;
	}

	public void setSubscriptions(List<Subscription> subscriptions) {
		this.subscriptions = subscriptions;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result
				+ ((frequency == null) ? 0 : frequency.hashCode());
		result = prime * result + ((hash == null) ? 0 : hash.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((instance == null) ? 0 : instance.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result
				+ ((notifyByPhone == null) ? 0 : notifyByPhone.hashCode());
		result = prime * result + ((phone == null) ? 0 : phone.hashCode());
		result = prime * result + ((pin == null) ? 0 : pin.hashCode());
		result = prime * result
				+ ((subscriptions == null) ? 0 : subscriptions.hashCode());
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
		Subscriber other = (Subscriber) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (frequency != other.frequency)
			return false;
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
		if (notifyByPhone != other.notifyByPhone)
			return false;
		if (phone == null) {
			if (other.phone != null)
				return false;
		} else if (!phone.equals(other.phone))
			return false;
		if (pin == null) {
			if (other.pin != null)
				return false;
		} else if (!pin.equals(other.pin))
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
		return "Subscriber [id=" + id + ", instance=" + instance + ", name="
				+ name + ", email=" + email + ", pin=" + pin + ", hash=" + hash
				+ ", phone=" + phone + ", notifyByPhone=" + notifyByPhone
				+ ", frequency=" + frequency + ", subscriptions="
				+ subscriptions + "]";
	}

}
