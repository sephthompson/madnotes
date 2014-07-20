package com.devcru.madnotes;

import java.io.IOException;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Register extends HttpServlet {

	private static final long serialVersionUID = 1029849297015934427L;
	private static String pattern1 = "[\\s\\,]";
	private static String pattern2 = "[\\s]";

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		DBConnectionManager dbCon = new DBConnectionManager();
		String url = dbCon.getUrl();
		
		String user_id = request.getParameter("user_id");
		String email = request.getParameter("email").replaceAll(pattern1, "");
		String password = request.getParameter("password").replaceAll(pattern2, "");
		String firstname = request.getParameter("firstname");
		String lastname = request.getParameter("lastname");

		String preppedQuery = "INSERT INTO accounts (email, password)"
				+ " VALUES (?, ?);" + "INSERT INTO profiles (user_id)"
				+ " SELECT accounts.user_id" + " FROM accounts"
				+ " WHERE accounts.email = ?;" + "UPDATE profiles SET"
				+ " firstname = ?, lastname = ?"
				+ " FROM accounts WHERE accounts.user_id = profiles.user_id"
				+ " AND accounts.email = ?;";
		
		Connection con = null;
		PreparedStatement ps = null;

		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println(e.getMessage());
		}
		
		try {
			con = DriverManager.getConnection(url);

			System.out.println(preppedQuery); // DEBUG

			try {
				ps = con.prepareStatement(preppedQuery);

				ps.setString(1, email);
				ps.setString(2, password);
				ps.setString(3, email);
				ps.setString(4, firstname);
				ps.setString(5, lastname);
				ps.setString(6, email);

				System.out.println("SQL=" + preppedQuery.toString()); // DEBUG

				ps.execute();

				System.out.println("Executed=" + preppedQuery.toString()); // DEBUG

				HttpSession session = request.getSession(true);
				session.setAttribute("user_id", user_id);
				session.setAttribute("email", email);
				session.setAttribute("password", password);
				session.setAttribute("firstname", firstname);
				session.setAttribute("lastname", lastname);
				
				// RESPONSE TO CLIENT
				response.sendRedirect("welcome.jsp");
				
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (ps != null) {
			        try {
			            ps.close();
			        } catch (SQLException e) {
			        	e.printStackTrace();
			        	}
			    }
			}

		} catch (SQLException e) {
			System.out.println("SQLException occured: " + e.getMessage());
			e.printStackTrace();
		} finally {
		    if (con != null) {
		        try {
		            con.close();
		        } catch (SQLException e) {
		        	e.printStackTrace();
		        	}
		    }
		}
	}
}
