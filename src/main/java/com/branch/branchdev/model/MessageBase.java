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
@Table(name="message_base")
public class MessageBase {

	@Id @GeneratedValue
	private int mid;
	
	private int aid;
	private int cid;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date timestamp;

	@Override
	public String toString() {
		return "MessageBase [mid=" + mid + ", aid=" + aid + ", cid=" + cid + ", timestamp=" + timestamp + "]";
	}

	public MessageBase(int mid, int aid, int cid, Date timestamp) {
		super();
		this.mid = mid;
		this.aid = aid;
		this.cid = cid;
		this.timestamp = timestamp;
	}

	public MessageBase() {}

	public int getMid() {
		return mid;
	}

	public void setMid(int mid) {
		this.mid = mid;
	}

	public int getAid() {
		return aid;
	}

	public void setAid(int aid) {
		this.aid = aid;
	}

	public int getCid() {
		return cid;
	}

	public void setCid(int cid) {
		this.cid = cid;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}
		
}
