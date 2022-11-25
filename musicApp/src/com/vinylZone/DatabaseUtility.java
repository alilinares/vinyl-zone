package com.vinylZone;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.annotation.Resource;
import javax.sql.DataSource;

public class DatabaseUtility {
	
	// PROPERTIES 
	private Connection connection=null;
	private PreparedStatement statement=null;
	private ResultSet resultSet=null;

	/**
	 * @param dataSource
	 */
	
	public DatabaseUtility() {
		super();
	}


   /* Summary of Methods 
    * close(Connection c, Statement s, ResultSet rs);
    * getUsers();
    * 
    * */
	
	protected void  makeConnection(DataSource dataSource) throws SQLException {
		// Make a connection to the database, else return null
		this.connection = dataSource.getConnection();
	}
	

	protected void closeConnection() {
		
		try {
			if(this.resultSet != null) {
				this.resultSet.close();
			}
			if(this.statement != null) {
				this.statement.close();
			}
			if(this.connection != null) {
				this.connection.close(); // return connection use. 
			}
		}
		catch(Exception exc) {
			exc.printStackTrace();
		}finally {
			// clean - just in case. 
			this.connection = null;
			this.statement = null;
			this.resultSet = null;
		}
	}
	
	// GENERAL CRUD OPERATIONS FOR USERS
	/* public ArrayList<User> getListOfUsers() throws Exception {
		// return a list of users in the db
		ArrayList<User> users = new ArrayList<User>();
		
		// set variables
		Connection dbConnection = null;
		Statement sqlStatement = null;
		ResultSet resultSet=null;
		
		try{
			
			// get a connection
			dbConnection = dataSource.getConnection();
			
			// create sql statement object
			String sql = "SELECT * FROM users ORDER BY userID DESC";	
			sqlStatement = dbConnection.createStatement();
			
			
			// execute query
			resultSet = sqlStatement.executeQuery(sql);
			
			// process result set
			
			while (resultSet.next()) {
				// retrieve data from row
				int userId = resultSet.getInt("userID");
				String username = resultSet.getString("username");
				String firstName = resultSet.getString("firstName");
				String lastName = resultSet.getString("lastName");
				String email = resultSet.getString("email");
				String password = resultSet.getString("password");
				
				User u = new User(username,firstName,lastName,email,password);
				u.setUserId(userId);
				
				// add user object to list
				users.add(u);
			}
			
	    return users;
		}
		finally {
			// close JDBC objects;
			close(dbConnection,sqlStatement,resultSet);
		}
	}
	*/
	// USER CRUD OPERATIONS
	
	// Create a user
	public int addUser(User u){
		String sql = "INSERT INTO USER (username,firstName,lastName,email,password) VALUES(?,?,?,?,?)";
		int rowsAffected = 0;
		
		// prepare statement. 
		try {
			statement = this.connection.prepareStatement(sql);
			// Get values from user object
			statement.setString(1,u.getUsername());
			statement.setString(2,u.getFirstName());
			statement.setString(3,u.getLastName());
			statement.setString(4,u.getEmail());
			statement.setString(5,u.getPassword());
			// execute sql insert
			rowsAffected = statement.executeUpdate(); // 0 if nothing happened
			
		} catch (SQLException e) {
			System.out.println("Something went wrong from addUser()");
			e.printStackTrace();
		}
		
		return rowsAffected;
	}
	
	// Retrieve a user
	public User getUserById(int uid) {
		String sql = "SELECT * FROM users WHERE users.userID=?";
		User user=null;
		
		try {	
			//prepare sql statement
			statement = this.connection.prepareStatement(sql);
			
			//Set values on placeholders found in sql query string
			statement.setString(1, String.valueOf(uid));
					
			// execute sql and store results
			this.resultSet = statement.executeQuery();
			
			if(resultSet.next()) {
				String username = resultSet.getString("username");
				String firstName = resultSet.getString("firstName");
				String lastName = resultSet.getString("lastName");
				String email = resultSet.getString("email");
				String password = resultSet.getString("password");
				// File profilePhoto = new File(results.getString("profilePhoto"));
				String bio = resultSet.getString("biography");
				String role = resultSet.getString("role");
		
				//instantiate user object
				user = new User(username,firstName,lastName,email,password);
				user.setUserId(uid);
				}
			}catch(Exception e) {
				System.out.println("trouble finding user...");
				e.printStackTrace();
			}
			return user;		
		
	}

	public User getUserByUsernameAndPassword(String u, String p){
		
		String sql = "SELECT * FROM users WHERE users.username=? AND users.password=?"; // sql query
		User user=null;
		
		try {	
			//prepare sql statement
			statement = this.connection.prepareStatement(sql);
			//Set values on placeholders found in sql query string 
			statement.setString(1, u);
			statement.setString(2, p);
					
			// execute sql and store results
			this.resultSet = statement.executeQuery();
			
			if(resultSet.next()) {
				int userId = Integer.parseInt(resultSet.getString("userID"));
				String username = resultSet.getString("username");
				String firstName = resultSet.getString("firstName");
				String lastName = resultSet.getString("lastName");
				String email = resultSet.getString("email");
				String password = resultSet.getString("password");
				// File profilePhoto = new File(results.getString("profilePhoto"));
				String bio = resultSet.getString("biography");
				String role = resultSet.getString("role");
		
				//instantiate user object
				user = new User(username,firstName,lastName,email,password);
				}
			}catch(Exception e) {
				System.out.println("user not found");
				e.printStackTrace();
			}
			return user;
	}

	public User getUserByUsernameAndEmail(String u, String e) {
		String sql = "SELECT * FROM USER WHERE USER.username=? AND USER.email=?";
		User user=null;
		
		try {	
			//prepare sql statement
			statement = this.connection.prepareStatement(sql);
			
			//Set values on placeholders found in sql query string
			statement.setString(1, u);
			statement.setString(2, e);
					
			// execute sql and store results
			this.resultSet = statement.executeQuery();
			
			if(resultSet.next()) {
				int    userID = Integer.valueOf(resultSet.getString("userID"));
				String username = resultSet.getString("username");
				String firstName = resultSet.getString("firstName");
				String lastName = resultSet.getString("lastName");
				String email = resultSet.getString("email");
				String password = resultSet.getString("password");
				// File profilePhoto = new File(results.getString("profilePhoto"));
				String bio = resultSet.getString("biography");
				String role = resultSet.getString("role");
		
				//instantiate user object
				user = new User(userID,username,firstName,lastName,email,password);
				}
			}catch(Exception exception) {
				System.out.println("trouble finding user...");
				exception.printStackTrace();
			}
			return user;
	}
	
	// Update a user
	/*
	public void updateUser(User u) throws Exception {		
		//jdbc objects
		Connection dbConnection=null;
		PreparedStatement statement=null;
		
				
		try {
			
			// make connection
			dbConnection = this.dataSource.getConnection();
			
			// create sql insert
			String sql = "UPDATE users "
					+"SET username=?, firstName=?, lastName=?, email=?, password=?, biography=?, profilePhoto=?"
					+" WHERE userID=?";
			
			//prepare statement
			statement = dbConnection.prepareStatement(sql);
			
			statement.setString(1, u.getUsername());
			statement.setString(2, u.getFirstName());
			statement.setString(3, u.getLastName());
			statement.setString(4, u.getEmail());
			statement.setString(5, u.getPassword());
			statement.setString(6, u.getBio());
			
			if(u.getProfilePhoto() != null) {
				statement.setBinaryStream(7, u.getProfilePhoto());
			}else {
				statement.setBinaryStream(7, null);
			}
			statement.setInt(8,u.getUserId());
					
			// execute sql insert
			statement.execute();
			
			}finally {
				// clean up jdbc objects. 
				close(dbConnection,statement,null);
			}		
		
	}

	public void deleteUser(int userId) throws Exception {
		//jdbc objects
		Connection dbConnection=null;
		PreparedStatement statement=null;
		
				
		try {
			
			// make connection
			dbConnection = this.dataSource.getConnection();
			
			// create sql insert
			String sql = "DELETE FROM users WHERE userID=?";
			
			//prepare statement
			statement = dbConnection.prepareStatement(sql);
			statement.setInt(1,userId);
					
			// execute sql insert
			statement.execute();
			
			}finally {
				// clean up jdbc objects. 
				close(dbConnection,statement,null);
			}
	}
	
	public User verifyUser(String e) throws SQLException, FileNotFoundException{
		User user = null;
		//jdbc objects
		Connection dbConnection=null;
		PreparedStatement statement=null;
		ResultSet results=null;
		
		try {
			// make connection
			dbConnection = dataSource.getConnection();
			
			// create sql insert
			String sql = "SELECT * FROM users WHERE users.email='"+e+"'";
			
			//prepare statement
			statement = dbConnection.prepareStatement(sql);
			
			// execute sql insert
			results = statement.executeQuery();
			
			if(results.next()) {
				int userId = Integer.parseInt(results.getString("userID"));
				String username=results.getString("username");
				String lastName=results.getString("firstName");
				String firstName=results.getString("lastName");
				String password=results.getString("password");
				String email=results.getString("email");
				String biography=results.getString("biography");
				
				user = new User(userId,username,firstName,lastName,email,password,biography);
				}
			return user;
			}finally {
		    // clean up jdbc objects. 
			close(dbConnection,statement,results);
			}
		}

	*/
	
}
