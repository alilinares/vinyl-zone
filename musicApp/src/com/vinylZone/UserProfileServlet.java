package com.vinylZone;

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
 * Servlet implementation class UserProfileServlet
 */
@WebServlet("/UserProfileServlet")
public class UserProfileServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	private  ApplicationDao applicationDao;
	
	@Resource(name="jdbc/vinylZone") // Get Resource - JDBC Driver and other meta  (context.xml)
	protected DataSource dataSource;
       
    /**
     * @throws Exception 
     * @see HttpServlet#HttpServlet()
     */
    public UserProfileServlet() throws Exception {
        super();
        this.applicationDao = new ApplicationDao();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// load user information and send to userProfile.jsp
		
		try {
			loadUser(request,response);
		} catch (Exception e) {
			System.out.println("an error occurred while loading user...");
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	private void loadUser(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// get user data and send them too userProfile
		User user = (User) request.getAttribute("USER");
		
		//get specific user 	
		//get from database
		user = applicationDao.findUserByUsernameAndEmail(user.getUsername(),user.getEmail(),this.dataSource);
		
		//store user in request
		request.setAttribute("USER", user);
		
		//send to userProfile
		RequestDispatcher dispatcher = request.getRequestDispatcher("/profile.jsp");
		dispatcher.forward(request, response);
		
	}

}
