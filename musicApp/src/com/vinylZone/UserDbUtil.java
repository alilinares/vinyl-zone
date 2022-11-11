package com.vinylZone;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.sql.DataSource;

public class UserDbUtil {

	private DataSource dataSource;
	private String databaseUser = "vinylzone";
	private String databasePassword = "vinylZone100";

	

	/**
	 * @param dataSource
	 */
	public UserDbUtil(DataSource dataSource) {
		super();
		this.dataSource = dataSource;
	}
	
	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public String getDatabaseUser() {
		return databaseUser;
	}

	public void setDatabaseUser(String databaseUser) {
		this.databaseUser = databaseUser;
	}

	public String getDatabasePassword() {
		return databasePassword;
	}

	public void setDatabasePassword(String databasePassword) {
		this.databasePassword = databasePassword;
	}

// Methods
	
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
			String sql = "SELECT * FROM users ORDER BY lastName";	
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
				
				
				// create user object
				User u = new User(username,firstName,lastName,email,password);
				// store user id of user
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
	
	public int loginUser(String u, String p)throws Exception {
		// return 1 if user in database -1 if no user exists
		
		//jdbc objects
		Connection dbConnection=null;
		PreparedStatement statement=null;
		ResultSet results=null;
		ArrayList<String> user = new ArrayList<String>();
		
		try {
			// make connection
			dbConnection = dataSource.getConnection();
			
			// create sql insert
			String sql = "SELECT username,password FROM users WHERE users.username='"+u+"'"
					+" AND users.password='"+p+ "'" ;
			
			//prepare statement
			statement = dbConnection.prepareStatement(sql);
			
			// execute sql insert
			results = statement.executeQuery();
			while(results.next()) {
				String username=results.getString("username");
				String password=results.getString("password");
				user.add(username);
				user.add(password);
			}
			if(user.size()==2) {
				return 1;
			}else {
				return -1;
			}
		}finally {
		    // clean up jdbc objects. 
			close(dbConnection,statement,results);
		}
	}

	public User retrieveUser(String userId) throws Exception {
		User user=null;
		
		//jdbc objects
		Connection dbConnection=null;
		PreparedStatement statement=null;
		ResultSet results=null;
		
				
		try {
			int id = Integer.parseInt(userId);
			
			// make connection
			dbConnection = this.dataSource.getConnection();
			
			// create sql insert
			String sql = "SELECT * FROM users WHERE users.userID=?";
					
			//prepare statement
			statement = dbConnection.prepareStatement(sql);
			statement.setInt(1, id);
					
			// execute sql insert
			results = statement.executeQuery();
			if(results.next()) {
				String username = results.getString("username");
				String firstName = results.getString("firstName");
				String lastName = results.getString("lastName");
				String email = results.getString("email");
				String password = results.getString("password");
			//make user object
			user = new User(username,firstName,lastName,email,password);
			user.setUserId(id);
			
			}else {
				throw new Exception("Could not find user.");
			}
			return user;

			}finally {
				// clean up jdbc objects. 
				close(dbConnection,statement,results);
			}
	}

}
