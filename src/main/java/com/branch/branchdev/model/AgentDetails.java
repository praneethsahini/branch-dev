package com.branch.branchdev.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table(name="agent_details")
public class AgentDetails {

	@Id @GeneratedValue
	private int aid;
	private String firstName;
	private String lastName;
	private String address;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date timestamp;
	
	public AgentDetails(int cid, String firstName, String lastName, String address, Date timestamp) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.timestamp = timestamp;
	}
	public AgentDetails() {}
	
	@Override
	public String toString() {
		return "Customer [cid=" + aid + ", firstName=" + firstName + ", lastName=" + lastName + ", address=" + address
				+ ", timestamp=" + timestamp + "]";
	}
	
	
	
	public int getAid() {
		return aid;
	}
	public void setAid(int aid) {
		this.aid = aid;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Date getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}	
	
}
