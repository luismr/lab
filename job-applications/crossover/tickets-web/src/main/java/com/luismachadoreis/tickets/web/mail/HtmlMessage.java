package com.luismachadoreis.tickets.web.mail;

public class HtmlMessage extends Message implements IHtmlMessage {
	
	private String messageHtml;

	public HtmlMessage() {}

	public HtmlMessage(IHost host) {
		super(host);
	}

	/* (non-Javadoc)
	 * @see br.com.ligflat.api.talkalot.data.mail.IHtmlMessage#getMessageHtml()
	 */
	@Override
	public String getMessageHtml() {
		return messageHtml;
	}

	/* (non-Javadoc)
	 * @see br.com.ligflat.api.talkalot.data.mail.IHtmlMessage#setMessageHtml(java.lang.String)
	 */
	@Override
	public void setMessageHtml(String messageHtml) {
		this.messageHtml = messageHtml;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((messageHtml == null) ? 0 : messageHtml.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		HtmlMessage other = (HtmlMessage) obj;
		if (messageHtml == null) {
			if (other.messageHtml != null)
				return false;
		} else if (!messageHtml.equals(other.messageHtml))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "HtmlMessage [messageHtml=" + messageHtml + ", getTo()=" + getTo() + ", getFrom()=" + getFrom()
				+ ", getSubject()=" + getSubject() + ", getMessage()=" + getMessage() + ", getAttachment()="
				+ getAttachment() + ", getHost()=" + getHost() + "]";
	}

}
