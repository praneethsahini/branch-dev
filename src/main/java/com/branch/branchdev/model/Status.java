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
@Table(name="status")
public class Status {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int sid;	
	private String status_message;
	
	
	@Override
	public String toString() {
		return "Status [sid=" + sid + ", status_message=" + status_message + "]";
	}


	public Status(int sid, String status_message) {
		super();
		this.sid = sid;
		this.status_message = status_message;
	}


	public Status() {}


	public int getSid() {
		return sid;
	}


	public void setSid(int sid) {
		this.sid = sid;
	}


	public String getStatus_message() {
		return status_message;
	}


	public void setStatus_message(String status_message) {
		this.status_message = status_message;
	}
		
}
