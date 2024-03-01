package com.nt.dao;

import com.nt.model.User;

public interface UserDao {
	boolean addUser(User user);
	boolean isValidUser(String email, String password);
}
