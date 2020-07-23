package com.revature.models;

public class Record {
	
	private int recordId; 
	private int userId; 
	private int accountId;
	private double balance; 
	
	
	public int getRecordId() {
		return recordId;
	}
	public void setRecordId(int recordId) {
		this.recordId = recordId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getAccountId() {
		return accountId;
	}
	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	@Override
	public String toString() {
	
		
		String type="checking", status="pending";
		
		if (accountId%2==0)type="saving";
		if (accountId>2)status="Open";
		if (accountId>4)status="Closed";
		if (accountId>6)status="Denied";
		
		return "Record [recordId=" + recordId + ", userId=" + userId + ", accountType=" + type+ ", accountStatus="
		          + status+ ", balance="+ balance + "]";
	}
}
