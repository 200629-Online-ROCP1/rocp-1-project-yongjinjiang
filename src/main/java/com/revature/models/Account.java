package com.revature.models;

public class Account {
	  
	private int accountId; // primary key
	 
	  private AccountStatus status;
	  private AccountType type;
	  
	  
	public Account(AccountStatus status, AccountType type) {
			super();
			this.status = status;
			this.type = type;
			accountId=type.getTypeId()+(status.getStatusId()-1)*2;
		}  
	  
	
	public AccountStatus getStatus() {
		return status;
	}
	public void setStatus(AccountStatus status) {
		this.status = status;
	}
	public AccountType getType() {
		return type;
	}
	public void setType(AccountType type) {
		this.type = type;
	}

	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}
	  

}
