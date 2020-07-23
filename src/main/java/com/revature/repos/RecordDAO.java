package com.revature.repos;

import java.util.List;

import com.revature.models.Account;
import com.revature.models.Record;
import com.revature.models.User;

public interface RecordDAO {
	
	public List<Record> findAll(int userId);

}
