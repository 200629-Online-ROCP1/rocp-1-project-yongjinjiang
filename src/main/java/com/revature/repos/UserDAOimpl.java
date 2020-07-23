package com.revature.repos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.revature.models.Account;
import com.revature.models.AccountStatus;
import com.revature.models.AccountType;
import com.revature.models.CreatUserDTO;
import com.revature.models.Role;
import com.revature.models.User;
import com.revature.util.ConnectionUtil;

public class UserDAOimpl implements UserDAO {
	
	//This is a design pattern called a singleton where only one implementation of a class can exist at a time.
		//This is not necessary for your project, you may leave it out and create multiple DAO objects. 
		private static UserDAO repo = new UserDAOimpl();
		
		private UserDAOimpl() {};
		
		public static UserDAO getInstance() {
			return repo;
		}
		
		@Override
		public boolean insert(CreatUserDTO l) { //create an new user
			System.out.println("In Inserting user");
			User user=new User(l.username,l.password,l.firstName,l.lastName, l.email,new Role(l.role));
			Account account=new Account(new AccountStatus(l.status),new AccountType(l.type));
			
			
			try(Connection conn = ConnectionUtil.getConnection()){
				int index =0;
				String sql = "INSERT INTO bank.Users(username,password,first_name,last_name,email,role) "
						+ "VALUES(?,?,?,?,?,?);";
				
				PreparedStatement statement = conn.prepareStatement(sql);
				statement.setString(++index, user.getUsername());
				statement.setString(++index, user.getPassword());
				statement.setString(++index, user.getFirstName());
				statement.setString(++index, user.getLastName());
				statement.setString(++index, user.getEmail());
				statement.setString(++index, user.getRole().getRole());
				
				
				System.out.println(statement);
				
				if(statement.execute()) {
					
//					User u=findByUserNameInUsersList(user.getUsername());
					//write records using userid and accound info 
					return true;
				}
				
			}catch (SQLException e) {
				System.out.println(e);
			}
			return false;
		}

		
		@Override
		public User findByUserNameInUsersList(String userName) {
			System.out.println("in findByUserNameInUsersList");
			try(Connection conn = ConnectionUtil.getConnection()){
				String sql = "SELECT * FROM bank.users where username= '" + userName+ "';";
				System.out.println("SQL query="+sql);
				
				PreparedStatement statement = conn.prepareStatement(sql);
//				statement.setString(1, firstName);
			
				ResultSet result = statement.executeQuery();
				
				
				if(result.next()) {
					return new User(result.getInt("userid"), result.getString("username"),
					        result.getString("password"),result.getString("first_name"),
							result.getString("last_name"),result.getString("email"),result.getString("role"));
				}
				
			}catch(SQLException e) {
				System.out.println(e);
			}
			return null;
		}
		
		
		@Override
		public boolean insertRecord(CreatUserDTO l) {
			System.out.println("in insertRecord");
			
			System.out.println("In Inserting user");
			User user=new User(l.username,l.password,l.firstName,l.lastName, l.email,new Role(l.role));
			Account account=new Account(new AccountStatus(l.status),new AccountType(l.type));
			
			user=findByUserNameInUsersList(user.getUsername());
			
			try(Connection conn = ConnectionUtil.getConnection()){
				int index =0;
				String sql = "INSERT INTO bank.records(userId,accountId,balance) "
						+ "VALUES(?,?,?);";
				
				PreparedStatement statement = conn.prepareStatement(sql);
				statement.setInt(++index, user.getUserId());
				statement.setInt(++index, account.getAccountId());
				statement.setDouble(++index, l.firstDeposit);
				
				System.out.println(statement);
				
				if(statement.execute()) {
					return true;
				}
				
			}catch (SQLException e) {
				System.out.println(e);
			}
			return false;
		}

		
//		@Override
//		public boolean insertStatement(Celebrity celeb) {
//			System.out.println("In insertStatement");
//			
//			try(Connection conn = ConnectionUtil.getConnection()){
//				String sql = "INSERT INTO celebrity(first_name, last_name, stage_name, gender)"
//						+ "VALUES('"+celeb.getFirstName()+"','"+celeb.getLastName()+"','"+celeb.getStageName()+"','"+celeb.getGender()+"');";
//				
//				Statement statement = conn.createStatement();
//				
//				if(statement.execute(sql)) {
//					return true;
//				}
//			}catch(SQLException e) {
//				System.out.println(e);
//			}
//			return false;
//		}
//
//		@Override
//		public Celebrity findByFirstName(String firstName) {
//			System.out.println("in find by name");
//			try(Connection conn = ConnectionUtil.getConnection()){
//				String sql = "SELECT * FROM celebrity WHERE first_name = ?";
//				
//				PreparedStatement statement = conn.prepareStatement(sql);
//				statement.setString(1, firstName);
//				
//				ResultSet result = statement.executeQuery();
//				
//				if(result.next()) {
//					return new Celebrity(result.getInt("id"), result.getString("first_name"),
//							result.getString("last_name"), result.getString("stage_name"), 
//							result.getString("gender"));
//				}
//				
//			}catch(SQLException e) {
//				System.out.println(e);
//			}
//			return null;
//		}
//
//		@Override
//		public Set<Celebrity> selectAll() {
//			System.out.println("In Select All");
//			try(Connection conn = ConnectionUtil.getConnection()){
//				String sql = "SELECT * FROM celebrity;";
//				
//				Statement statement = conn.createStatement();
//				
//				Set<Celebrity> set = new HashSet<>();
//				
//				ResultSet result = statement.executeQuery(sql);
//				
//				while(result.next()) {
//					set.add(new Celebrity(result.getInt("id"), result.getString("first_name"),
//							result.getString("last_name"), result.getString("stage_name"), 
//							result.getString("gender")));
//				}
//				
//				return set;
//				
//			}catch(SQLException e) {
//				System.out.println(e);
//			}
//			
//			return null;
//		}

}
