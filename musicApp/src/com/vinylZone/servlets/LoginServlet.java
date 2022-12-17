package com.vinylZone.servlets;

import com.vinylZone.daos.ApplicationDao;
import com.vinylZone.beans.User;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 * Servlet implementation class LoginServlet
 */

@WebServlet("/LoginServlet")

@MultipartConfig(
		  fileSizeThreshold = 1024 * 1024 * 1, // 1 MB
		  maxFileSize = 1024 * 1024 * 10,      // 10 MB
		  maxRequestSize = 1024 * 1024 * 100   // 100 MB
		)

public class LoginServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	@Resource(name="jdbc/vinylZone") // Get Resource - JDBC Driver and other meta  (context.xml)
	protected DataSource dataSource;
	
	private ApplicationDao applicationDao;
	
    public LoginServlet() {
        super();
        this.applicationDao = new ApplicationDao();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/* Send users to the login page AKA request dispatching */
		RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
		dispatcher.forward(request, response);
	}
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/* Handle the post request made by the user from login.jsp 
		 * */
		try {
			// appropriate method to process the method
			loginUser(request,response);
		} catch (Exception e) {
			System.out.println("failed to log in user.\n");
			e.printStackTrace();
		}
	}
	
	private void loginUser(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		// Get submission values from login.jsp
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		
		// Instantiate a ApplicationDao Object
		
		//Verify and Retrieve User from database
		User user = applicationDao.findUserByUsernameAndPassword(username,password,this.dataSource);
		
		// Does user exist?
		if(user != null) {
			Cookie userCookie = new Cookie("USER_ID",String.valueOf(user.getUserId()));
			
			//setting cookie to expiry in 30 mins
			userCookie.setMaxAge(30*60);
			response.addCookie(userCookie);
			//Attach user obj to the request
			request.setAttribute("USER", user);
			
			//Dispatch user to UserProfileServlet with the request and response objects.
			RequestDispatcher dispatcher = request.getRequestDispatcher("UserProfileServlet");
			dispatcher.forward(request, response);
			
		}else {
			
			//Dispatch user to loginFail.jsp 
			RequestDispatcher dispatcher = request.getRequestDispatcher("/loginFail.jsp");
			dispatcher.forward(request, response);
		}	
	}

}
