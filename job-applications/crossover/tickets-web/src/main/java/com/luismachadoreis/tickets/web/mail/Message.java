package com.luismachadoreis.tickets.web.mail;

public class Message implements IMessage {

	private String to;
	private String from;
	private String subject;
	private String message;
	
	private IAttachment attachment;

	private IHost host;
	private String name;
	
	public Message() {}
	
	public Message(final IHost host) {
		this.host = host;
		this.from = host.getDefaultFrom();
	}

	/* (non-Javadoc)
	 * @see br.com.ligflat.api.talkalot.data.mail.IMessage#getTo()
	 */
	@Override
	public String getTo() {
		return to;
	}

	/* (non-Javadoc)
	 * @see br.com.ligflat.api.talkalot.data.mail.IMessage#setTo(java.lang.String)
	 */
	@Override
	public void setTo(String to) {
		this.to = to;
	}

	/* (non-Javadoc)
	 * @see br.com.ligflat.api.talkalot.data.mail.IMessage#getFrom()
	 */
	@Override
	public String getFrom() {
		return from;
	}

	/* (non-Javadoc)
	 * @see br.com.ligflat.api.talkalot.data.mail.IMessage#setFrom(java.lang.String)
	 */
	@Override
	public void setFrom(String from) {
		this.from = from;
	}

	/* (non-Javadoc)
	 * @see br.com.ligflat.api.talkalot.data.mail.IMessage#getSubject()
	 */
	@Override
	public String getSubject() {
		return subject;
	}

	/* (non-Javadoc)
	 * @see br.com.ligflat.api.talkalot.data.mail.IMessage#setSubject(java.lang.String)
	 */
	@Override
	public void setSubject(String subject) {
		this.subject = subject;
	}

	/* (non-Javadoc)
	 * @see br.com.ligflat.api.talkalot.data.mail.IMessage#getMessage()
	 */
	@Override
	public String getMessage() {
		return message;
	}

	/* (non-Javadoc)
	 * @see br.com.ligflat.api.talkalot.data.mail.IMessage#setMessage(java.lang.String)
	 */
	@Override
	public void setMessage(String message) {
		this.message = message;
	}

	/* (non-Javadoc)
	 * @see br.com.ligflat.api.talkalot.data.mail.IMessage#getAttachment()
	 */
	@Override
	public IAttachment getAttachment() {
		return attachment;
	}

	/* (non-Javadoc)
	 * @see br.com.ligflat.api.talkalot.data.mail.IMessage#setAttachment(br.com.ligflat.api.talkalot.data.mail.IAttachment)
	 */
	@Override
	public void setAttachment(IAttachment attachment) {
		this.attachment = attachment;
	}

	/* (non-Javadoc)
	 * @see br.com.ligflat.api.talkalot.data.mail.IMessage#getHost()
	 */
	@Override
	public IHost getHost() {
		return host;
	}

	/* (non-Javadoc)
	 * @see br.com.ligflat.api.talkalot.data.mail.IMessage#setHost(br.com.ligflat.api.talkalot.data.mail.IHost)
	 */
	@Override
	public void setHost(IHost host) {
		this.host = host;
	}

	/* (non-Javadoc)
	 * @see br.com.ligflat.api.talkalot.data.mail.IMessage#getName()
	 */
	@Override
	public String getName() {
		return this.name;
	}
	
	/* (non-Javadoc)
	 * @see br.com.ligflat.api.talkalot.data.mail.IMessage#setName(java.lang.String)
	 */
	@Override
	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((attachment == null) ? 0 : attachment.hashCode());
		result = prime * result + ((from == null) ? 0 : from.hashCode());
		result = prime * result + ((host == null) ? 0 : host.hashCode());
		result = prime * result + ((message == null) ? 0 : message.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((subject == null) ? 0 : subject.hashCode());
		result = prime * result + ((to == null) ? 0 : to.hashCode());
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
		Message other = (Message) obj;
		if (attachment == null) {
			if (other.attachment != null)
				return false;
		} else if (!attachment.equals(other.attachment))
			return false;
		if (from == null) {
			if (other.from != null)
				return false;
		} else if (!from.equals(other.from))
			return false;
		if (host == null) {
			if (other.host != null)
				return false;
		} else if (!host.equals(other.host))
			return false;
		if (message == null) {
			if (other.message != null)
				return false;
		} else if (!message.equals(other.message))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (subject == null) {
			if (other.subject != null)
				return false;
		} else if (!subject.equals(other.subject))
			return false;
		if (to == null) {
			if (other.to != null)
				return false;
		} else if (!to.equals(other.to))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Message [to=" + to + ", from=" + from + ", subject=" + subject + ", message=" + message
				+ ", attachment=" + attachment + ", host=" + host + ", name=" + name + "]";
	}

}
