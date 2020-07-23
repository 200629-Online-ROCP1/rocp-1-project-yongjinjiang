package com.revature.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtil {
	
	public static Connection getConnection() throws SQLException {
		
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
//		String url = "jdbc:postgresql://localhost:5432/demos";
		String url = "jdbc:postgresql://localhost:5432/pokerdex";
		String username = "postgres";
		String password = "newPassword"; 
		
		return DriverManager.getConnection(url, username, password);
	}

}
