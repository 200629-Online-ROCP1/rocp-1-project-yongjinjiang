package com.revature.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.models.LoginDTO;
import com.revature.models.LoginReturn;
import com.revature.models.Record;
import com.revature.models.Role;
import com.revature.models.User;
import com.revature.util.ConnectionUtil;



public class LoginService {
	
	public LoginReturn login(LoginDTO l) {
		
		try (Connection conn = ConnectionUtil.getConnection()){
			String sql = "SELECT * FROM bank.users;";
			PreparedStatement statement = conn.prepareStatement(sql);
			
			ResultSet result = statement.executeQuery();			
			
			List<User> list = new ArrayList<>();
			
		
			if(result.next()) {
				User a= new User();
				a.setUserId(result.getInt("userid"));
				a.setUsername(result.getString("username"));
				a.setPassword(result.getString("password"));
				a.setFirstName(result.getString("first_name"));
				a.setLastName(result.getString("last_name"));
				a.setEmail(result.getString("email"));
				a.setRole(new Role(result.getString("role")));
				list.add(a);	
			}
			
			for(User u: list) { 
		          if(l.username.equalsIgnoreCase(u.getUsername())&&l.password.equalsIgnoreCase(u.getPassword())) {
			         return new LoginReturn(true,u); 
		    }
			}
	
		return new LoginReturn(false,null); 
	}catch(SQLException e) {
		e.printStackTrace();
		return new LoginReturn(false,null); 
	}
		
	}

}
