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

import javax.sql.DataSource;

public class DatabaseUtility {

	private DataSource dataSource;

	

	/**
	 * @param dataSource
	 */
	public DatabaseUtility(DataSource dataSource) {
		super();
		this.dataSource = dataSource;
	}
	
	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}


// Methods

	private void close(Connection dbConnection, Statement sqlStatement, ResultSet resultSet) {
		
		try {
			if(resultSet != null) {
				resultSet.close();
			}
			if(sqlStatement != null) {
				sqlStatement.close();
			}
			if(dbConnection != null) {
				dbConnection.close(); // return connection use. 
			}
		}
		catch(Exception exc) {
			exc.printStackTrace();
		}
	}
	
	public ArrayList<User> getUsers() throws Exception {
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

	
	public void addUser(User u) throws Exception{
		
		// jdbc objects
		Connection dbConnection = null;
		PreparedStatement statement = null;
		
		try {
			dbConnection = dataSource.getConnection();
			
			// create sql insert for use'
			String sql = "INSERT INTO users (username,firstName,lastName,email,password) VALUES(?,?,?,?,?)";
			
			statement = dbConnection.prepareStatement(sql);
			
			// Get values from User
			statement.setString(1,u.getUsername());
			statement.setString(2,u.getFirstName());
			statement.setString(3,u.getLastName());
			statement.setString(4,u.getEmail());
			statement.setString(5,u.getPassword());
			
			// execute sql insert
			statement.executeUpdate();
			
		}finally {
		    // clean up jdbc objects. 
			close(dbConnection,statement,null);
		}
	}
	
	
	public User verifyUser(String u, String p)throws Exception {
		//
		User user = null;
		//jdbc objects
		Connection dbConnection=null;
		PreparedStatement statement=null;
		ResultSet results=null;
		
		try {
			// make connection
			dbConnection = dataSource.getConnection();
			
			// create sql insert
			String sql = "SELECT * FROM users WHERE users.username='"+u+"'"
					+" AND users.password='"+p+ "'" ;
			
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

	
	public User retrieveUser(int userId) throws Exception {
		User user=null;
		
		//jdbc objects
		Connection dbConnection=null;
		PreparedStatement statement=null;
		ResultSet results=null;
				
		try {
			
			// make connection
			dbConnection = this.dataSource.getConnection();
			
			// create sql insert
			String sql = "SELECT * FROM users WHERE users.userID=?";
					
			//prepare statement
			statement = dbConnection.prepareStatement(sql);
			statement.setInt(1, userId);
					
			// execute sql insert
			results = statement.executeQuery();
			if(results.next()) {
				String username = results.getString("username");
				String firstName = results.getString("firstName");
				String lastName = results.getString("lastName");
				String email = results.getString("email");
				String password = results.getString("password");
				String biography = results.getString("biography");
				Blob profilePhoto = results.getBlob("profilePhoto");
				
			//make user object
			user = new User(username,firstName,lastName,email,password);
			
			user.setBio(biography);
			user.setUserId(userId);
			
			
			}else {
				throw new Exception("Could not find user.");
			}
			return user;

			}finally {
				// clean up jdbc objects. 
				close(dbConnection,statement,results);
			}
	}
	
	
	public User retrieveUser(String usrName) throws Exception{
		User user=null;
		
		//jdbc objects
		Connection dbConnection=null;
		PreparedStatement statement=null;
		ResultSet results=null;
				
		try {
			
			// attempt connection
			dbConnection = this.dataSource.getConnection();
			
			// create sql insert query
			String sql = "SELECT * FROM users WHERE users.username=?";
					
			//prepare sql statement
			statement = dbConnection.prepareStatement(sql);
			statement.setString(1, usrName);
					
			// execute sql and store results
			results = statement.executeQuery();
			
			if(results.next()) {
				int userId = Integer.parseInt(results.getString("userID"));
				String username = results.getString("username");
				String firstName = results.getString("firstName");
				String lastName = results.getString("lastName");
				String email = results.getString("email");
				String password = results.getString("password");
				// File profilePhoto = new File(results.getString("profilePhoto"));
				String bio = results.getString("biography");
				String role = results.getString("role");
				
			//make user object
			user = new User(username,firstName,lastName,email,password);
			
			}else {
				throw new Exception("Could not find user.");
			}
			return user;

			}finally {
				// clean up jdbc objects. 
				close(dbConnection,statement,results);
			}
		
	}
	
	

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

	
	
}
