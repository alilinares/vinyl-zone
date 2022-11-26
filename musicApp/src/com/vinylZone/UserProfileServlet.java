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
		String command = request.getParameter("command");
		User user = (User) request.getAttribute("USER");
		
		try {
			switch(command) {
			case "UPDATE_PROFILE":
				updateUser(request,response);
				break;
			case "DELETE_ACCOUNT":
				deleteUser(request,response);
				break;
			default:
				loadUser(request,response);
			}
		} catch (Exception e) {
			System.out.println("an error occurred while loading user...");
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String command = request.getParameter("command");
		User user = (User) request.getAttribute("USER");
		
		switch(command) {
		case "UPDATE_PROFILE":
			updateUser(request,response);
			break;
		case "DELETE_ACCOUNT":
			deleteUser(request,response);
			break;
		default:
			try {
				loadUser(request,response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("Command not found");
		}
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

	private void updateUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		//update user data
		String username = request.getParameter("username");
		String firstname = request.getParameter("firstName");
		String lastname = request.getParameter("lastName");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		// String biography = request.getParameter("bio");
		// String profilePhoto = request.getParameter("profilePhoto");
		
		//store user information in user object
		User user = new User(username,firstname,lastname,email,password);
		
		//call application function
		applicationDao.updateUserProfile(user,this.dataSource); // pass user object and dataSource
		
		//Send user back to profile - fake refresh
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("profile.jsp");
		requestDispatcher.forward(request, response);
		
		
	}
	
	private void deleteUser(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		User user = (User) request.getAttribute("USER");
		
		this.applicationDao.deleteUser(user, this.dataSource);
		
		//Send user back to profile - fake refresh
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("index.jsp");
		requestDispatcher.forward(request, response);
		
	}


}
