package com.revature.controllers;

import java.util.List;

import com.revature.services.AcountsService;
import com.revature.models.Record;

public class AcountsController {
	
	
	private final AcountsService as = new AcountsService();
	public List<Record> findAll(int userid) {
		return as.findAll(userid);
	}

}
