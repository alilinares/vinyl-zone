package com.vinylZone;

import java.io.IOException;
import java.util.ArrayList;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 * Servlet implementation class UserControllerServlet
 */
@WebServlet("/UserControllerServlet")
public class UserControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private UserDbUtil userDbUtil;
	
	// resource injection 
	@Resource(name="jdbc/vinylZone")
	private DataSource dataSource;
	

	@Override
	public void init() throws ServletException {
		super.init();
		
		try {
			// create an instance of UserDBUtil & pass in the conn pool
			userDbUtil = new UserDbUtil(dataSource);
			
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
		userDbUtil.deleteUser(userId);
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
		// create user object to store/send data 
		User user = new User(username,firstName,lastName,email,password);
		user.setUserId(userId);
		// update database with userId
		userDbUtil.updateUser(user);
		// send them back to their userProfile
		listUsers(request,response);
	}

	private void loadUser(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//get specific user 
		String userId = request.getParameter("userId");
		
		//get from database
		User user= userDbUtil.retrieveUser(userId);
		
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
		int verified = this.userDbUtil.loginUser(username,password);
		if(verified == 1) {
			// user exists and is verified send them to their userProfile.jsp
			RequestDispatcher dispatcher = request.getRequestDispatcher("/userProfile.jsp");
			dispatcher.forward(request, response);
		}else {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/loginFail.jsp");
			dispatcher.forward(request, response);
		}	
	}

	private void registerUser(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// Get inputted user information from register.jsp
		String username = request.getParameter("username");
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
        // create a user account
		User user = new User(username,firstName,lastName,email,password);
		
		// insert user into the database
		this.userDbUtil.addUser(user);
		
		// send back to list of users.
		//listUsers(request,response);
		listUsers(request,response);
	}

	private void listUsers(HttpServletRequest request, HttpServletResponse response) throws Exception{
			// get users
			ArrayList<User> users = this.userDbUtil.getUsers();
			// add users to request object
			request.setAttribute("USER_LIST", users);
			// send to JSP
			RequestDispatcher dispatcher = request.getRequestDispatcher("/list-users.jsp");
			dispatcher.forward(request, response);
		
	}

}
