package model;

import java.util.Set;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement // sending xml
public class Response {

	private boolean status;
	private String message;
	private Set <String> keys;

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void setMessages(Set<String> keys) {
		this.keys=keys;
		
	}
}
