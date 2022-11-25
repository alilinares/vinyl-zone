package com.vinylZone;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
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

/**
 * Servlet implementation class UserControllerServlet
 */
@WebServlet("/UserControllerServlet")
@MultipartConfig(
		  fileSizeThreshold = 1024 * 1024 * 1, // 1 MB
		  maxFileSize = 1024 * 1024 * 10,      // 10 MB
		  maxRequestSize = 1024 * 1024 * 100   // 100 MB
		)
public class UserControllerServlet extends HttpServlet {
	
	// Attributes
	
	private static final long serialVersionUID = 1L;
	
	private DatabaseUtility databaseUtility;
	
	@Resource(name="jdbc/vinylZone") // resource injection 
	private DataSource dataSource;
	

	@Override
	public void init() throws ServletException {
		super.init();
		
		try {
			// create an instance of UserDBUtil & pass in the conn pool
			databaseUtility = new DatabaseUtility(dataSource);
			
		}catch(Exception e) {
			throw new ServletException(e);
		}
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
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
	}
	
	private void deleteUser(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// get user id
		int userId = Integer.parseInt(request.getParameter("userId"));
		// delete user from database
		databaseUtility.deleteUser(userId);
		//send them back to list-users.jsp
		listUsers(request, response);
	}

	private void updateUser(HttpServletRequest request, HttpServletResponse response) throws Exception{
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

	private void loadUser(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// get user data and send them too userProfile
		User user = null;
		
		//get specific user 
		String userId = request.getParameter("userId");
		
		String username = request.getParameter("username");
		
		//get from database
		if(userId != null) {
			user = databaseUtility.retrieveUser(Integer.parseInt(userId));
		}else {
			user = databaseUtility.retrieveUser(username);
		}
		
		//store user in request
		request.setAttribute("USER", user);
		
		//send to userProfile
		RequestDispatcher dispatcher = request.getRequestDispatcher("/userProfile.jsp");
		dispatcher.forward(request, response);
		
	}

	private void loginUser(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// get user login cred.
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		// verify user login cred.
		User user = this.databaseUtility.verifyUser(username,password);
		
		if(user != null) {
			// user exists and is verified send them to their userProfile.jsp
			request.setAttribute("USER", user);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/userProfile.jsp");
			dispatcher.forward(request, response);
		}else {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/loginFail.jsp");
			dispatcher.forward(request, response);
		}	
	}

	
	private void registerUser(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		// Get Form data
		String username = request.getParameter("username");
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
        //Create a user instance
		User user = new User(username,firstName,lastName,email,password);

		
		// insert user into the database
		
		this.databaseUtility.addUser(user);
		
		// send back to list of users.
		//listUsers(request,response);
		loadUser(request,response);
	}

	
	private void listUsers(HttpServletRequest request, HttpServletResponse response) throws Exception{
			// get users
			ArrayList<User> users = this.databaseUtility.getUsers();
			// add users to request object
			request.setAttribute("USER_LIST", users);
			// send to JSP
			RequestDispatcher dispatcher = request.getRequestDispatcher("/list-users.jsp");
			dispatcher.forward(request, response);
		
	}


	private void resetPassword(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
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

	private void sendTemporaryPassword(String email) {
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


}
