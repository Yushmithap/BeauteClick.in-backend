package com.niit.shopgirlbackend.dao;

import java.util.List;

import com.niit.shopgirlbackend.model.User;

public interface UserDAO {
	//write just method declarations that you want to implement it in another class that is in DAO

	public List<User> list();
	
	public User get(String id);
	
	public User validate(String id, String password);
	
	public boolean save(User user);
	
	public boolean update(User user);
	
	
}
