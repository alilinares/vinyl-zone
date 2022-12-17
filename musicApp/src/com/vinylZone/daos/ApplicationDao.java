package com.vinylZone.daos;

import com.vinylZone.beans.User;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.annotation.Resource;
import javax.sql.DataSource;


public class ApplicationDao {
	
	// Attributes
	DatabaseUtility databaseUtility;
	
	// Constructor
	public ApplicationDao(){
		this.databaseUtility = new DatabaseUtility();
	}
	

    // USER OPERATIONS
	
	public int registerUser(User user, DataSource dataSource) {
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
	
	public User findUserByUsernameAndEmail(String un,String em, DataSource dataSource) {
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
    
	public User findUserByUsernameAndPassword(String username,String password, DataSource dataSource) {
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

	public void updateUserProfile(User user,DataSource dataSource){
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
	
	public void deleteUser(User user,DataSource dataSource) {
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
	
	public ArrayList<User> getListOfUsers(DataSource dataSource) {
		
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
