package com.nt.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.nt.dao.UserDao;
import com.nt.model.User;
import com.nt.util.DBUtil;

public class UserDaoImpl implements UserDao{

	@Override
	public boolean addUser(User user) {
		String query = "INSERT INTO user (first_name,last_name,email, password,role_id,active) VALUES (?, ?, ?,?,?,?)";

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement pt = connection.prepareStatement(query)) {

        	pt.setString(1, user.getFirstName());
        	pt.setString(2, user.getLastName()); 
           pt.setString(3, user.getEmail());
           pt.setString(4, user.getPassword());
           pt.setLong(5,102 );
           pt.setBoolean(6, true);

            int rowsAffected = pt.executeUpdate();

            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    
		
	}

	@Override
	public boolean isValidUser(String email, String password){
		
		 String query = "SELECT * FROM user WHERE email = ? AND password = ?";
    	 try (Connection connection = DBUtil.getConnection();
             PreparedStatement pt = connection.prepareStatement(query)) {

            pt.setString(1, email);
            pt.setString(2, password);

            ResultSet resultSet = pt.executeQuery();

            return resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }}


