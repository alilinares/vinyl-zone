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
				command = "LIST";
			}
			// route to the appropriate method
			switch(command) {
			  case "LIST" : 
				  listUsers(request,response);
				  break;
			  case "REGISTER": 
				  registerUser(request,response);
			      break;
			  case "LOGIN":
				  loginUser(request,response);
				  break;
			  default:
				  listUsers(request,response);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void loginUser(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// get user login cred.
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		// verify user login cred.
		int verified = this.userDbUtil.verifyUser(username,password);
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
