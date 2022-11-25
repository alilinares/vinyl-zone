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
	



	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	/*protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
		try {
			// read command parameter
			String command = request.getParameter("command");
			
			//if command is missing, default = listing users
			if (command == null) {
				command = "LIST_USERS";
			}
			// route to the appropriate method
			switch(command) {
			  case "LIST_USERS" : 
				  listUsers(request,response);
				  break;
			  case "LOAD_USER":
				  loadUser(request,response);
				  break;
			  case "DELETE_USER":
				  deleteUser(request,response);
				  break;
			  default:
				  listUsers(request,response);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			// read command parameter
			String command = request.getParameter("command");
			
			//if command is missing, default = listing users
			if (command == null) {
				command = "LIST_USERS";
			}
			// route to the appropriate method
			switch(command) {
			  case "LIST_USERS" : 
				  listUsers(request,response);
				  break;
			  case "REGISTER_USER": 
				  registerUser(request,response);
			      break;
			  case "LOGIN_USER":
				  loginUser(request,response);
				  break;
			  case "LOAD_USER":
				  loadUser(request,response);
				  break;
			  case "UPDATE_USER":
				  updateUser(request,response);
				  break;
			  case "DELETE_USER":
				  deleteUser(request,response);
				  break;
			  case "RESET_PASSWORD":
				  resetPassword(request,response);
				  break;
			  case "FORGOT_PASSWORD":
				  resetPassword(request,response);
				  break;
			  default:
				  listUsers(request,response);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/
	

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
			databaseUtility.makeConnection(dataSource);
			// Retrieve user from database if user exists, else return null 
			user = databaseUtility.getUserById(uid);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
		    // close connection
			databaseUtility.closeConnection();
		}
		return user;
	}

/*
	protected void deleteUser(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// get user id
		int userId = Integer.parseInt(request.getParameter("userId"));
		// delete user from database
		databaseUtility.deleteUser(userId);
		//send them back to list-users.jsp
		listUsers(request, response);
	}

	protected void updateUser(HttpServletRequest request, HttpServletResponse response) throws Exception{
		// get new user data
		int userId = Integer.parseInt(request.getParameter("userId"));
		String username = request.getParameter("username");
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String bio = request.getParameter("bio");
		
		// handle file upload
		InputStream inputStream = null;
		// Obtain photo with Part
		Part filePart = request.getPart("profilePhoto");
		if (filePart != null) {
	            // prints out some information for debugging
	            System.out.println(filePart.getName());
	            System.out.println(filePart.getSize());
	            System.out.println(filePart.getContentType());
	             
	            // obtains input stream of the upload file
	            inputStream = filePart.getInputStream();
	        }
		
		// create user object to store/send data 
		User user = new User(userId, username,firstName,lastName,email,password,bio,inputStream);
		
		// update database with userId
		databaseUtility.updateUser(user);
		
		// send them back to their userProfile
		listUsers(request,response);
	}

	protected void listUsers(HttpServletRequest request, HttpServletResponse response) throws Exception{
			// get users
			ArrayList<User> users = this.databaseUtility.getListOfUsers();
			// add users to request object
			request.setAttribute("USER_LIST", users);
			// send to JSP
			RequestDispatcher dispatcher = request.getRequestDispatcher("/list-users.jsp");
			dispatcher.forward(request, response);
		
	}

	protected void resetPassword(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
		// get form data
		String email = request.getParameter("email");
		
		//verify
		User user = this.databaseUtility.verifyUser(email);
		if(user !=null) {
			sendTemporaryPassword(email);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/resetPassword.jsp");
			dispatcher.forward(request, response);
		}
	}

	protected void sendTemporaryPassword(String email) {
		// Set up the SMTP server.
		java.util.Properties props = new java.util.Properties();
		props.put("mail.smtp.host", "smtp.myisp.com");
		Session session = Session.getDefaultInstance(props, null);

		// Construct the message
		String to = email;
		String from = "DO_NOT_REPLAY@vinylzone.com";
		String subject = "RESET PASSWORD";
		Message msg = new MimeMessage(session);
		try {
		    msg.setFrom(new InternetAddress(from));
		    msg.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
		    msg.setSubject(subject);
		    msg.setText("22u22");
		    // Send the message.
		    Transport.send(msg);
		} catch (MessagingException e) {
		    // Error.
		}
		
	}




	
*/
}
