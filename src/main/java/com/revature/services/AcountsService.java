package com.revature.services;

import java.util.List;

import com.revature.repos.RecordDAO;
import com.revature.repos.RecordDAOimpl;
import com.revature.models.Record;

public class AcountsService {
	

	private final RecordDAO rd = RecordDAOimpl.getInstance();
	
	public List<Record> findAll(int userid) {
		return rd.findAll(userid);
	}

	

}
