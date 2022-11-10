package com.vinylZone;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**z
 * Servlet implementation class JdbcTestServlet
 */
@WebServlet("/JdbcTestServlet")
public class JdbcTestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	// Resource Injection
	
	@Resource(name="jdbc/vinylZone")
	private DataSource dataSource; // reference

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// set up printWriter
		PrintWriter out = response.getWriter();
		response.setContentType("text/plain");
		
		// connect to database
		Connection dbConnect = null;
		Statement statement = null;
		ResultSet resultSet = null;
		
		
		try{
			// test db connection to the connection pool
			dbConnect = dataSource.getConnection();
			
			// create  a sql statement
			String sql = "SELECT * FROM users";
			statement = dbConnect.createStatement();
			
			// execute
			
			resultSet = statement.executeQuery(sql);
			
			// process result set
			while(resultSet.next()) {
				String email = resultSet.getString("email");
				out.println(email);
			}
		}catch(Exception exc) {
			exc.printStackTrace();
		}
		

	}

}
