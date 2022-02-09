package com.branch.branchdev.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table(name="message_details")
public class MessageDetails {

	@Id
	private int mid;	
	private String message;

	@Temporal(TemporalType.TIMESTAMP)
	private Date timestamp;

	@Override
	public String toString() {
		return "MessageDetails [mid=" + mid + ", message=" + message + ", timestamp=" + timestamp + "]";
	}

	public MessageDetails(int mid, String message, Date timestamp) {
		super();
		this.mid = mid;
		this.message = message;
		this.timestamp = timestamp;
	}

	public MessageDetails() {}

	public int getMid() {
		return mid;
	}

	public void setMid(int mid) {
		this.mid = mid;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}
	
	

	
		
}
