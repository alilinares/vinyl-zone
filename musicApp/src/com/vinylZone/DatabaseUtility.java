package com.vinylZone;

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
	
	protected DatabaseUtility() {
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
	// USER CRUD OPERATIONS
	
	// Create a user
	protected int addUser(User u){
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
	protected User getUserById(int uid) {
		String sql = "SELECT * FROM USER WHERE USER.userID=?";
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
				user = new UserBuilder().setUsername(username).setFirstName(firstName).setLastName(lastName).setEmail(email).setPassword(password).createUser();
				user.setUserId(uid);
				}
			}catch(Exception e) {
				System.out.println("trouble finding user...");
				e.printStackTrace();
			}
			return user;		
		
	}
	

	protected User getUserByUsernameAndPassword(String u, String p){
		
		String sql = "SELECT * FROM USER WHERE USER.username=? AND USER.password=?"; // sql query
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
				user = new UserBuilder().setUsername(username).setFirstName(firstName).setLastName(lastName).setEmail(email).setPassword(password).createUser();
				}
			}catch(Exception e) {
				System.out.println("user not found");
				e.printStackTrace();
			}
			return user;
	}

	protected User getUserByUsernameAndEmail(String u, String e) {
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
				user = new UserBuilder().setUserId(userID).setUsername(username).setFirstName(firstName).setLastName(lastName).setEmail(email).setPassword(password).createUser();
				}
			}catch(Exception exception) {
				System.out.println("trouble finding user...");
				exception.printStackTrace();
			}
			return user;
	}
	
	protected void updateUser(User u) {	
		// create sql update query
		String sql = "UPDATE USER "
				+"SET username=?, firstName=?, lastName=?, email=?, password=? "
				+"WHERE userID=?";
		try {
			
			//prepare sql statement
			statement = this.connection.prepareStatement(sql);
			//Set values on placeholders found in sql query string
			statement.setString(1, u.getUsername());
			statement.setString(2, u.getFirstName());
			statement.setString(3, u.getLastName());
			statement.setString(4, u.getEmail());
			statement.setString(5, u.getPassword());
			statement.setInt(8,u.getUserId());
					
			// execute sql insert
			statement.execute();
			
			}catch(Exception e) {
				System.out.println("updating user error");
				e.printStackTrace();
			}
	}
	
	protected void deleteUser(User user) {
		// create sql insert
		String sql = "DELETE FROM USER WHERE USER.username=? AND USER.email=?";
				
		try {
			
			//prepare statement
			statement = this.connection.prepareStatement(sql);
			statement.setString(1,user.getUsername());
			statement.setString(2,user.getEmail());
					
			// execute sql insert
			statement.execute();
			
			}catch(Exception e) {
				System.out.println("deleting user error");
				e.printStackTrace();
			}
	}

	protected ArrayList<User> getAllUsers(){	
		ArrayList<User> users = new ArrayList<>(); 
		
		// create sql insert
		String sql = "SELECT * FROM USER ORDER BY USER.userID DESC";	
		try {
			Statement statement;
			//prepare statement
			 statement = this.connection.createStatement();
					
			// execute sql insert
			this.resultSet = statement.executeQuery(sql);
			while (resultSet.next()) {
				// retrieve data from row
				int userId = resultSet.getInt("userID");
				String username = resultSet.getString("username");
				String firstName = resultSet.getString("firstName");
				String lastName = resultSet.getString("lastName");
				String email = resultSet.getString("email");
				String password = resultSet.getString("password");
				
				User u = new UserBuilder().setUserId(userId).setUsername(username).setFirstName(firstName).setLastName(lastName).setEmail(email).setPassword(password).createUser();
				u.setUserId(userId);
				
				// add user object to list
				users.add(u);
			}
			}catch(Exception e) {
				System.out.println("deleting user error");
				e.printStackTrace();
			}
		return users;
	}
}
