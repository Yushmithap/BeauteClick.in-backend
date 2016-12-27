package com.niit.shopgirlbackend.daoimpl;

import com.niit.shopgirlbackend.dao.UserDAO;

public class UserDAOImpl implements UserDAO {

	public boolean isValidCredentials(String email, String password) {
		
		if(email.equals("yushmitha6a1a@gmail.com") && password.equals("yushyush")){
			return true;
		}
		else{
		
		return false;
	}

}
}