package com.niit.dao;

import java.util.List;

import com.niit.model.Connect;

public interface UserDao {
	public void addUser(Connect user);
	public List<Connect> listUsers();
	public boolean isExistingUser(Connect user);
	public Connect getUserByUsername(String username);
	public Connect getEmailid(String email,String password);
	public Connect getUserId(long userId);


}
