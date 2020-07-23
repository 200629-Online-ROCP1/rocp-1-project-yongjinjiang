package com.revature.models;
	public class AccountType {
	private int typeId; // primary key
    private String type; // not null, unique
    
	public AccountType(String type) {
		super();
		this.type = type;
		if (type.equalsIgnoreCase("checking")) typeId=1;
		if (type.equalsIgnoreCase("savinging")) typeId=2;
		}

	public int getTypeId() {
		return typeId;
	}

	
	    
	}


