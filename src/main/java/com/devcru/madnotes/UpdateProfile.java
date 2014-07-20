package com.devcru.madnotes;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class UpdateProfile extends HttpServlet{
	
	private static final long serialVersionUID = 3543421213400202288L;
	
	// NEED TO IMPLEMENT PreparedStatements and resource closing

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		DBConnectionManager dbCon = new DBConnectionManager();
		String url = dbCon.getUrl();
		
		HttpSession session = request.getSession(true);
		
		String email = (String) session.getAttribute("email");
		String firstname = request.getParameter("firstname");
		String lastname = request.getParameter("lastname");
		String about = request.getParameter("about");

		// Re-analyze this query to determine if referencing email is necessary.
		// Might be able to simply use the user_id attribute for when we implement
		// PreparedStatements.
		
		String updateQuery = "UPDATE profiles SET firstname = '"
				+ firstname + "', lastname = '" + lastname + "', about = '"
				+ about + "' FROM accounts WHERE accounts.user_id = profiles.user_id"
				+ " AND accounts.email = '"	+ email + "';";

		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println(e.getMessage());
		}
		try {
			Connection con = DriverManager.getConnection(url);
			Statement stmt = con.createStatement();
			stmt.execute(updateQuery);

			// We need to store these in session in order for the refresh after update to work.
			// If these currently exist in session, this will update them to the new change(s).
			session.setAttribute("firstname", firstname);
			session.setAttribute("lastname", lastname);
			session.setAttribute("about", about);
			
			// System.out.println("email: " + email); // Debug output

			response.sendRedirect("profile.jsp");

		} catch (SQLException e) {
			System.out.println("SQLException occured: " + e.getMessage());
			e.printStackTrace();
		}
	}
}