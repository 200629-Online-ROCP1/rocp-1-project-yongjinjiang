package com.revature.repos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.models.*;
import com.revature.models.Record;
import com.revature.util.ConnectionUtil;

public class RecordDAOimpl implements RecordDAO{
	
	private static RecordDAO repo = new RecordDAOimpl();
	
	private RecordDAOimpl() {};
	
	public static RecordDAO getInstance() {
		return repo;
	}
	
	@Override
	public List<Record> findAll(int userId) {
	     	System.out.println("In findall acounts for a given user");
	
			try(Connection conn = ConnectionUtil.getConnection()){
				String sql = "SELECT * FROM bank.records where userid="+userId;
				PreparedStatement statement = conn.prepareStatement(sql);
//				statement.setString(1, firstName);
				
				
				ResultSet result = statement.executeQuery();
				
				
//				result.last(); 
//				System.out.println("Number of rows: "+result.getRow());
//				result.beforeFirst();
				
//				 ResultSetMetaData rsmd = result.getMetaData();
//				 String name = rsmd.getColumnName(1);
//				 System.out.println(name);
//				 name = rsmd.getColumnName(2);
//				 System.out.println(name);
//				System.out.println(result.getInt("recordid"));
//				System.out.println(result.getInt("userid"));
				
				List<Record> list = new ArrayList<>();
				
				while(result.next()) {
					Record a= new Record();
					a.setRecordId(result.getInt("recordid"));
					a.setUserId(result.getInt("userid"));
					a.setAccountId(result.getInt("accountid"));
					a.setBalance(result.getDouble("balance"));
					list.add(a); 
				}
			
				return list;
			}catch(SQLException e) {
				System.out.println(e);
			}
			return null;
		}	
		
		
		
}
