package com.branch.branchdev.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.*;


@Entity
@Table(name="customer")
public class Customer {

	@Id @GeneratedValue
	private int cid;
	private String firstName;
	private String lastName;
	private String address;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date timestamp;
	
	public Customer(int cid, String firstName, String lastName, String address, Date timestamp) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.timestamp = timestamp;
	}
	public Customer() {}
	
	@Override
	public String toString() {
		return "Customer [cid=" + cid + ", firstName=" + firstName + ", lastName=" + lastName + ", address=" + address
				+ ", timestamp=" + timestamp + "]";
	}
	
	
	
	public int getCid() {
		return cid;
	}
	public void setCid(int cid) {
		this.cid = cid;
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
