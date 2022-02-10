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
@Table(name="message_base")
public class MessageBase {

	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long mid;
	
	@Column(name = "aid", nullable = false)
	private long aid;
	
	@Column(name = "cid", unique = true, nullable = false)
	private long cid;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date timestamp;

	@Override
	public String toString() {
		return "MessageBase [mid=" + mid + ", aid=" + aid + ", cid=" + cid + ", timestamp=" + timestamp + "]";
	}

	public MessageBase(long aid, long cid, Date timestamp) {
		super();
//		this.mid = mid;
		this.aid = aid;
		this.cid = cid;
		this.timestamp = timestamp;
	}

	public MessageBase() {}

	public long getMid() {
		return mid;
	}

	public void setMid(long mid) {
		this.mid = mid;
	}

	public long getAid() {
		return aid;
	}

	public void setAid(long aid) {
		this.aid = aid;
	}

	public long getCid() {
		return cid;
	}

	public void setCid(long cid) {
		this.cid = cid;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}
		
}
