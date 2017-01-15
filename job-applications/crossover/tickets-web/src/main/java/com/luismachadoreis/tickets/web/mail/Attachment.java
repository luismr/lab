package com.luismachadoreis.tickets.web.mail;

public class Attachment implements IAttachment {

	private String name;
	private String description;
	private String fileName;

	public Attachment() {}

	/* (non-Javadoc)
	 * @see br.com.ligflat.api.talkalot.data.mail.IAttachment#getName()
	 */
	@Override
	public String getName() {
		return name;
	}

	/* (non-Javadoc)
	 * @see br.com.ligflat.api.talkalot.data.mail.IAttachment#setName(java.lang.String)
	 */
	@Override
	public void setName(String name) {
		this.name = name;
	}

	/* (non-Javadoc)
	 * @see br.com.ligflat.api.talkalot.data.mail.IAttachment#getDescription()
	 */
	@Override
	public String getDescription() {
		return description;
	}

	/* (non-Javadoc)
	 * @see br.com.ligflat.api.talkalot.data.mail.IAttachment#setDescription(java.lang.String)
	 */
	@Override
	public void setDescription(String description) {
		this.description = description;
	}

	/* (non-Javadoc)
	 * @see br.com.ligflat.api.talkalot.data.mail.IAttachment#getFileName()
	 */
	@Override
	public String getFileName() {
		return fileName;
	}

	/* (non-Javadoc)
	 * @see br.com.ligflat.api.talkalot.data.mail.IAttachment#setFileName(java.lang.String)
	 */
	@Override
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((fileName == null) ? 0 : fileName.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		Attachment other = (Attachment) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (fileName == null) {
			if (other.fileName != null)
				return false;
		} else if (!fileName.equals(other.fileName))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Attachment [name=" + name + ", description=" + description + ", fileName=" + fileName + "]";
	}
	
}
