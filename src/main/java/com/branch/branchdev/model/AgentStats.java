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
@Table(name="agent_stats")
public class AgentStats {

	@Id @GeneratedValue
	private int aid;
	private float rating;
	
	@Column(name = "max_num_customers")
	private int maxNumCustomers;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date timestamp;

	@Override
	public String toString() {
		return "AgentStats [aid=" + aid + ", rating=" + rating + ", maxNumCustomers=" + maxNumCustomers + ", timestamp="
				+ timestamp + "]";
	}

	public AgentStats(int aid, float rating, int maxNumCustomers, Date timestamp) {
		super();
		this.aid = aid;
		this.rating = rating;
		this.maxNumCustomers = maxNumCustomers;
		this.timestamp = timestamp;
	}

	public AgentStats() {}

	public int getAid() {
		return aid;
	}

	public void setAid(int aid) {
		this.aid = aid;
	}

	public float getRating() {
		return rating;
	}

	public void setRating(float rating) {
		this.rating = rating;
	}

	public int getMaxNumCustomers() {
		return maxNumCustomers;
	}

	public void setMaxNumCustomers(int maxNumCustomers) {
		this.maxNumCustomers = maxNumCustomers;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

}
