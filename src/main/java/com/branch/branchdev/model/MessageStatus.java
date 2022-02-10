package com.branch.branchdev.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table(name="message_status")
public class MessageStatus {

	@Id
	@Column(name = "mid", unique = true, nullable = false)
	private long mid;	
	
	private int status;

	@Temporal(TemporalType.TIMESTAMP)
	private Date timestamp;

	@Override
	public String toString() {
		return "MessageStatus [mid=" + mid + ", status=" + status + ", timestamp=" + timestamp + "]";
	}

	public MessageStatus(long l, int status, Date timestamp) {
		super();
		this.mid = l;
		this.status = status;
		this.timestamp = timestamp;
	}

	public MessageStatus() {}

	public long getMid() {
		return mid;
	}

	public void setMid(long mid) {
		this.mid = mid;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}
	
	

	
		
}
