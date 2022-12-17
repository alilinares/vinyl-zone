package com.vinylZone.servlets;

import com.vinylZone.daos.ApplicationDao;
import com.vinylZone.beans.User;
import com.vinylZone.builders.UserBuilder;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 * Servlet implementation class RegisterUserServlet
 */
@WebServlet("/RegisterUserServlet")
public class RegisterUserServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	@Resource(name="jdbc/vinylZone") // Get Resource - JDBC Driver and other meta  (context.xml)
	protected DataSource dataSource;
	
	private ApplicationDao applicationDao;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterUserServlet() {
        super();
		this.applicationDao = new ApplicationDao();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// send user to register.jsp
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("registerUser.jsp");
		requestDispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Get registration form data
		String username = request.getParameter("username");
		String firstname = request.getParameter("firstName");
		String lastname = request.getParameter("lastName");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
        //Create a user instance
		User user = new UserBuilder().setUsername(username).setFirstName(firstname).setLastName(lastname).setEmail(email).createUser();
		
		//register user to the database
		int rowsAffected = applicationDao.registerUser(user,this.dataSource);
		//get user
		User loadedUser = (User) this.applicationDao.findUserByUsernameAndEmail(user.getUsername(),user.getEmail(), dataSource);
		
		if( rowsAffected > 0 ) {
			
			// make sure to include information for userProfileServlet
			request.setAttribute("USER",loadedUser);
			RequestDispatcher dispatcher = request.getRequestDispatcher("UserProfileServlet");
			dispatcher.forward(request, response);
			
		}else {
			
		}
		
	}

}
