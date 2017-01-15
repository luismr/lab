/**
 * 
 */
package notifications.data.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import notifications.data.domain.helper.EntryStatus;
import notifications.data.domain.id.EntryID;

/**
 * Notification Entries for Subscriptions
 * @author luismr
 *
 */
@Entity
@Table(name = "entries")
public class Entry implements Serializable {

	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private EntryID id;
	
	@JoinColumns({
		@JoinColumn(name = "id_subscriber", referencedColumnName = "id_subscriber", insertable = false, updatable = false),
		@JoinColumn(name = "id_category", referencedColumnName = "id_category", insertable = false, updatable = false)
	})
	@ManyToOne
	private Subscription subscription;

	@JoinColumn(name = "id_notification", referencedColumnName = "id", insertable = false, updatable = false)
	@ManyToOne
	private Notification notification;

	@Column(name = "date_created")
	private Date dateCreated;
	
	@Column(name = "date_schedule")
	private Date dateSchedule;
	
	@Column(name = "date_updated")
	private Date dateUpdated;
	
	@Column(name = "status")
	@Enumerated(EnumType.STRING)
	private EntryStatus status;

	public Entry() {}
	
	public EntryID getId() {
		return id;
	}

	public void setId(EntryID id) {
		this.id = id;
	}

	public Subscription getSubscription() {
		return subscription;
	}

	public void setSubscription(Subscription subscription) {
		this.subscription = subscription;
	}

	public Notification getNotification() {
		return notification;
	}

	public void setNotification(Notification notification) {
		this.notification = notification;
	}

	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	public Date getDateSchedule() {
		return dateSchedule;
	}

	public void setDateSchedule(Date dateSchedule) {
		this.dateSchedule = dateSchedule;
	}

	public Date getDateUpdated() {
		return dateUpdated;
	}

	public void setDateUpdated(Date dateUpdated) {
		this.dateUpdated = dateUpdated;
	}

	public EntryStatus getStatus() {
		return status;
	}

	public void setStatus(EntryStatus status) {
		this.status = status;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((dateCreated == null) ? 0 : dateCreated.hashCode());
		result = prime * result
				+ ((dateSchedule == null) ? 0 : dateSchedule.hashCode());
		result = prime * result
				+ ((dateUpdated == null) ? 0 : dateUpdated.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((notification == null) ? 0 : notification.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result
				+ ((subscription == null) ? 0 : subscription.hashCode());
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
		Entry other = (Entry) obj;
		if (dateCreated == null) {
			if (other.dateCreated != null)
				return false;
		} else if (!dateCreated.equals(other.dateCreated))
			return false;
		if (dateSchedule == null) {
			if (other.dateSchedule != null)
				return false;
		} else if (!dateSchedule.equals(other.dateSchedule))
			return false;
		if (dateUpdated == null) {
			if (other.dateUpdated != null)
				return false;
		} else if (!dateUpdated.equals(other.dateUpdated))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (notification == null) {
			if (other.notification != null)
				return false;
		} else if (!notification.equals(other.notification))
			return false;
		if (status != other.status)
			return false;
		if (subscription == null) {
			if (other.subscription != null)
				return false;
		} else if (!subscription.equals(other.subscription))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Entry [id=" + id + ", subscription=" + subscription
				+ ", notification=" + notification + ", dateCreated="
				+ dateCreated + ", dateSchedule=" + dateSchedule
				+ ", dateUpdated=" + dateUpdated + ", status=" + status + "]";
	}
	
}
