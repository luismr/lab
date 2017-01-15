/**
 * 
 */
package notifications.data.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import notifications.data.domain.helper.Affirmative;

/**
 * Notifications to delivery
 * 
 * @author luismr
 *
 */
@Entity
@Table(name = "notifications")
public class Notification implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@JoinColumns({
		@JoinColumn(name = "id_token", referencedColumnName = "id"), 
		@JoinColumn(name = "id_instance", referencedColumnName = "id_instance")})
	@ManyToOne
	private Token token;

	@MapsId("id_category")
	@JoinColumn(name = "id_category", referencedColumnName = "id")
	@ManyToOne
	private Category category;

	@Column(name = "title")
	private String title;
	
	@Column(name = "data", length = 300)
	private String data;
	
	@Column(name = "date_created")
	private Date dateCreated;
	
	@Column(name = "sync")
	@Enumerated(EnumType.STRING)
	private Affirmative sync;
	
	public Notification() {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Token getToken() {
		return token;
	}

	public void setToken(Token token) {
		this.token = token;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	public Affirmative getSync() {
		return sync;
	}

	public void setSync(Affirmative sync) {
		this.sync = sync;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((category == null) ? 0 : category.hashCode());
		result = prime * result + ((data == null) ? 0 : data.hashCode());
		result = prime * result
				+ ((dateCreated == null) ? 0 : dateCreated.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((token == null) ? 0 : token.hashCode());
		result = prime * result + ((sync == null) ? 0 : sync.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
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
		Notification other = (Notification) obj;
		if (category == null) {
			if (other.category != null)
				return false;
		} else if (!category.equals(other.category))
			return false;
		if (data == null) {
			if (other.data != null)
				return false;
		} else if (!data.equals(other.data))
			return false;
		if (dateCreated == null) {
			if (other.dateCreated != null)
				return false;
		} else if (!dateCreated.equals(other.dateCreated))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (token == null) {
			if (other.token != null)
				return false;
		} else if (!token.equals(other.token))
			return false;
		if (sync != other.sync)
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Notification [id=" + id + ", instance=" + token
				+ ", category=" + category + ", title=" + title + ", data="
				+ data + ", dateCreated=" + dateCreated + ", sync=" + sync
				+ "]";
	}
	
}
