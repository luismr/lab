package com.luismachadoreis.tickets.web.mail;

public class Host implements IHost {

	private static final int DEFAULT_SMTP_PORT = 25;
	private String name;
	private String fqdn;
	
	private int port;
	
	private boolean ssl;
	
	private String password;
	private String username;
	private String defaultFrom;
	
	public Host() {}
	
	public Host(final String fqdn) {
		this.name = fqdn;
		this.fqdn = fqdn;
		this.port = DEFAULT_SMTP_PORT;
	}
	
	public Host(final String fqdn, final int port) {
		this.name = fqdn;
		this.fqdn = fqdn;
		this.port = port;
	}
	
	public Host(final String name, final String fqdn, final int port) {
		this.fqdn = fqdn;
		this.name = name;
		this.port = port;
	}

	/* (non-Javadoc)
	 * @see br.com.ligflat.api.talkalot.data.mail.IHost#getName()
	 */
	@Override
	public String getName() {
		return name;
	}

	/* (non-Javadoc)
	 * @see br.com.ligflat.api.talkalot.data.mail.IHost#setName(java.lang.String)
	 */
	@Override
	public void setName(String name) {
		this.name = name;
	}

	/* (non-Javadoc)
	 * @see br.com.ligflat.api.talkalot.data.mail.IHost#getFqdn()
	 */
	@Override
	public String getFqdn() {
		return fqdn;
	}

	/* (non-Javadoc)
	 * @see br.com.ligflat.api.talkalot.data.mail.IHost#setFqdn(java.lang.String)
	 */
	@Override
	public void setFqdn(String fqdn) {
		this.fqdn = fqdn;
	}

	/* (non-Javadoc)
	 * @see br.com.ligflat.api.talkalot.data.mail.IHost#getPort()
	 */
	@Override
	public int getPort() {
		return port;
	}

	/* (non-Javadoc)
	 * @see br.com.ligflat.api.talkalot.data.mail.IHost#setPort(int)
	 */
	@Override
	public void setPort(int port) {
		this.port = port;
	}

	/* (non-Javadoc)
	 * @see br.com.ligflat.api.talkalot.data.mail.IHost#getUsername()
	 */
	@Override
	public String getUsername() {
		return this.username;
	}
	
	/* (non-Javadoc)
	 * @see br.com.ligflat.api.talkalot.data.mail.IHost#setUsername(java.lang.String)
	 */
	@Override
	public void setUsername(String username) {
		this.username = username;
	}

	/* (non-Javadoc)
	 * @see br.com.ligflat.api.talkalot.data.mail.IHost#getPassword()
	 */
	@Override
	public String getPassword() {
		return this.password;
	}
	
	/* (non-Javadoc)
	 * @see br.com.ligflat.api.talkalot.data.mail.IHost#setPassword(java.lang.String)
	 */
	@Override
	public void setPassword(String password) {
		this.password = password;
	}

	/* (non-Javadoc)
	 * @see br.com.ligflat.api.talkalot.data.mail.IHost#isSsl()
	 */
	@Override
	public boolean isSsl() {
		return ssl;
	}

	/* (non-Javadoc)
	 * @see br.com.ligflat.api.talkalot.data.mail.IHost#setSsl(boolean)
	 */
	@Override
	public void setSsl(boolean ssl) {
		this.ssl = ssl;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((defaultFrom == null) ? 0 : defaultFrom.hashCode());
		result = prime * result + ((fqdn == null) ? 0 : fqdn.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + port;
		result = prime * result + (ssl ? 1231 : 1237);
		result = prime * result + ((username == null) ? 0 : username.hashCode());
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
		Host other = (Host) obj;
		if (defaultFrom == null) {
			if (other.defaultFrom != null)
				return false;
		} else if (!defaultFrom.equals(other.defaultFrom))
			return false;
		if (fqdn == null) {
			if (other.fqdn != null)
				return false;
		} else if (!fqdn.equals(other.fqdn))
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
		if (port != other.port)
			return false;
		if (ssl != other.ssl)
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Host [name=" + name + ", fqdn=" + fqdn + ", port=" + port + ", ssl=" + ssl + ", password=" + password
				+ ", username=" + username + ", defaultFrom=" + defaultFrom + "]";
	}

	/* (non-Javadoc)
	 * @see br.com.ligflat.api.talkalot.data.mail.IHost#getDefaultFrom()
	 */
	@Override
	public String getDefaultFrom() {
		return this.defaultFrom;
	}
	
	/* (non-Javadoc)
	 * @see br.com.ligflat.api.talkalot.data.mail.IHost#setDefaultFrom(java.lang.String)
	 */
	@Override
	public void setDefaultFrom(final String defaultFrom) {
		this.defaultFrom = defaultFrom;
	}

}
