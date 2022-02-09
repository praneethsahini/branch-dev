package com.branch.branchdev.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.annotation.CreatedDate;


@Entity
@Table(name="agent_details")
public class AgentDetails {

	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long aid;
	private String firstName;
	private String lastName;
	private String address;
	
	@Temporal(TemporalType.TIMESTAMP)
	@CreatedDate
	private Date timestamp;
	
	public AgentDetails(String firstName, String lastName, String address) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
//		this.timestamp = timestamp;
	}
	public AgentDetails() {}
	
	@Override
	public String toString() {
		return "Customer [cid=" + aid + ", firstName=" + firstName + ", lastName=" + lastName + ", address=" + address
				+ ", timestamp=" + timestamp + "]";
	}
	
	
	
	public long getAid() {
		return aid;
	}
	public void setAid(long aid) {
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
