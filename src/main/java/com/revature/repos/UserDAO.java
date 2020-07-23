package com.revature.repos;

import java.util.Set;

import com.revature.models.*;

public interface UserDAO {

//	public boolean insert(User user, Account account);
	public boolean insert(CreatUserDTO l);
//	public boolean insertStatement(Celebrity celeb);
//	public User findByUserName(String userName);
	public User findByUserNameInUsersList(String userName);
	
	public boolean insertRecord(CreatUserDTO l);
	
//	public Set<User> selectAll();

}
