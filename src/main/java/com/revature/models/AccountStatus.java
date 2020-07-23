package com.revature.models;

public class AccountStatus {
	private int statusId; // primary key
	private String status; // not null, unique
	
	public AccountStatus(String status) {
		super();
		this.status = status;
		if (status.equalsIgnoreCase("pending")) statusId=1;
		if (status.equalsIgnoreCase("Open")) statusId=2;
		if (status.equalsIgnoreCase("closed")) statusId=3;
		if (status.equalsIgnoreCase("denied")) statusId=4;
	}

	public int getStatusId() {
		return statusId;
	}

	}
	  

