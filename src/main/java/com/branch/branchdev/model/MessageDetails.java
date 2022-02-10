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
@Table(name="message_details")
public class MessageDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "mchat_id")
	private long mchatId;
	
	@Column(name = "mid", unique = true, nullable = false)
	private long mid;	
	
	@Column(name = "agent_cust")
	private boolean agentCust;
	private String message;

	@Temporal(TemporalType.TIMESTAMP)
	private Date timestamp;

	@Override
	public String toString() {
		return "MessageDetails [mchatId=" + mchatId + ", mid=" + mid + ", agentCust=" + agentCust + ", message="
				+ message + ", timestamp=" + timestamp + "]";
	}

	public MessageDetails(long mid, boolean agentCust, String message, Date timestamp) {
		super();
//		this.mchatId = mchatId;
		this.mid = mid;
		this.agentCust = agentCust;
		this.message = message;
		this.timestamp = timestamp;
	}

	public MessageDetails() {}

	public long getMchatId() {
		return mchatId;
	}

	public void setMchatId(long mchatId) {
		this.mchatId = mchatId;
	}

	public long getMid() {
		return mid;
	}

	public void setMid(long mid) {
		this.mid = mid;
	}

	public boolean isAgentCust() {
		return agentCust;
	}

	public void setAgentCust(boolean agentCust) {
		this.agentCust = agentCust;
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
