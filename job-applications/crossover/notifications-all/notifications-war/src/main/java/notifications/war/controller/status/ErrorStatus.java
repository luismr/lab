package notifications.war.controller.status;

import org.springframework.http.HttpStatus;

public class ErrorStatus {

	private int code;
	private String error;
	private String message;
	private String remarks;

	public ErrorStatus() {}
	
	public ErrorStatus(HttpStatus status) {
		this(status, null);
	}

	public ErrorStatus(HttpStatus status, String remarks) {
		this.code = status.value();
		this.error = status.toString();
		this.message = status.getReasonPhrase();
		this.remarks = remarks;
	}
	
	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	@Override
	public String toString() {
		return "Error [code=" + code + ", error=" + error + ", message="
				+ message + ", remarks=" + remarks + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + code;
		result = prime * result + ((error == null) ? 0 : error.hashCode());
		result = prime * result + ((message == null) ? 0 : message.hashCode());
		result = prime * result + ((remarks == null) ? 0 : remarks.hashCode());
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
		ErrorStatus other = (ErrorStatus) obj;
		if (code != other.code)
			return false;
		if (error == null) {
			if (other.error != null)
				return false;
		} else if (!error.equals(other.error))
			return false;
		if (message == null) {
			if (other.message != null)
				return false;
		} else if (!message.equals(other.message))
			return false;
		if (remarks == null) {
			if (other.remarks != null)
				return false;
		} else if (!remarks.equals(other.remarks))
			return false;
		return true;
	}
	
}
