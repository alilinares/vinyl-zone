package com.vinylZone;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import javax.sql.DataSource;
import javax.mail.*;
import javax.mail.internet.*;


public class ApplicationDao {
	
	// Attributes
	DatabaseUtility databaseUtility;
	
	// Constructor
	public ApplicationDao(){
		this.databaseUtility = new DatabaseUtility();
	}
	

    // USER OPERATIONS
	
	protected int registerUser(User user, DataSource dataSource) {
		int rowsAffected = 0;
		
		try {
			// make database connection	
			this.databaseUtility.makeConnection(dataSource);
			// insert user into the database
			rowsAffected = this.databaseUtility.addUser(user);
			//close database connection
			this.databaseUtility.closeConnection();
			
		} catch (SQLException e) {
			System.out.println("connection failed");
			e.printStackTrace();
		}
		return rowsAffected;
	}
	
	protected User findUserByUsernameAndEmail(String un,String em, DataSource dataSource) {
		User user = null;
		try {
			// start a connection to the database
			databaseUtility.makeConnection(dataSource);
			// Retrieve user from database if user exists, else return null 
			user = databaseUtility.getUserByUsernameAndEmail(un,em);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
		    // close connection
			databaseUtility.closeConnection();
		}
		return user;	
	}
    
	protected User findUserByUsernameAndPassword(String username,String password, DataSource dataSource) {
		User user = null;
		try {
			// start a connection to the database
			databaseUtility.makeConnection(dataSource);
			// Retrieve user from database if user exists, else return null 
			user = databaseUtility.getUserByUsernameAndPassword(username,password);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
		    // close connection
			databaseUtility.closeConnection();
		}
		return user;	
	}
	
	protected User findUserByID(int uid, DataSource dataSource) {
		User user = null;
	
		try {
			// start a connection to the database
			this.databaseUtility.makeConnection(dataSource);
			// Retrieve user from database if user exists, else return null 
			user = this.databaseUtility.getUserById(uid);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
		    // close connection
			this.databaseUtility.closeConnection();
		}
		return user;
	}

	protected void updateUserProfile(User user,DataSource dataSource){
		// update user's information
		try {
			//start connection
			this.databaseUtility.makeConnection(dataSource);
			//update user information
			this.databaseUtility.updateUser(user);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
		    // close connection
			this.databaseUtility.closeConnection();
		}
	}
	
	protected void deleteUser(User user,DataSource dataSource) {
		// update user's information
		try {
			//start connection
			this.databaseUtility.makeConnection(dataSource);
			// delete user from database
			this.databaseUtility.deleteUser(user);
			System.out.println("User Deleted");
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
		    // close connection
			this.databaseUtility.closeConnection();
		}
		
	}
	
	protected ArrayList<User> getListOfUsers(DataSource dataSource) {
		
		ArrayList<User> users = null;
		try {
			//start connection
			this.databaseUtility.makeConnection(dataSource);
			System.out.println("connection success");
			
			users = this.databaseUtility.getAllUsers();
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
		    // close connection
			this.databaseUtility.closeConnection();
		}
		return users;
	}

}
