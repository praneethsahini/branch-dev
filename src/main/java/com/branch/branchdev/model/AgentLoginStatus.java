package com.branch.branchdev.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table(name="agent_login_status")
public class AgentLoginStatus {
//	@GeneratedValue(strategy = GenerationType.AUTO)
	
	@Id
	@Column(name = "aid", unique = true, nullable = false)
	private long aid;
	
	@Column(name = "login_status")
	private boolean loginStatus;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date timestamp;

	@Override
	public String toString() {
		return "AgentLoginStatus [aid=" + aid + ", loginStatus=" + loginStatus + ", timestamp=" + timestamp + "]";
	}

	public AgentLoginStatus(long aid, boolean loginStatus, Date timestamp) {
		super();
		this.aid = aid;
		this.loginStatus = loginStatus;
		this.timestamp = timestamp;
	}

	public AgentLoginStatus() {}

	public long getAid() {
		return aid;
	}

	public void setAid(long aid) {
		this.aid = aid;
	}

	public boolean isLoginStatus() {
		return loginStatus;
	}

	public void setLoginStatus(boolean loginStatus) {
		this.loginStatus = loginStatus;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	
	

}
