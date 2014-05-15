package com.devcru.madnotes;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Signin extends HttpServlet {

	private static final long serialVersionUID = 6736266448070943215L;

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String preppedQuery = "SELECT email, password FROM accounts, profiles WHERE email = ? AND password = ? AND accounts.user_id = profiles.user_id;";
		
		Connection con = null;
		PreparedStatement ps = null;
		
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println(e.getMessage());
		}

		try {
			String url = "jdbc:postgresql://ec2-54-227-242-149.compute-1.amazonaws.com:5432/dcn41adan5r6m3?user=ticuspaqnevrws&password=nk9tAnxNavHirES6_UxGkzLn9x";
			con = DriverManager.getConnection(url);
			ps = con.prepareStatement(preppedQuery);
			
			// Begin inner-try/catch for PreparedStatement execution
			try {
				ps = con.prepareStatement(preppedQuery);

				ps.setString(1, email);
				ps.setString(2, password);

				System.out.println("SQL=" + preppedQuery.toString()); // DEBUG

				ps.execute();

				System.out.println("Executed=" + preppedQuery.toString()); // DEBUG

				HttpSession session = request.getSession(true);
				session.setAttribute("email", email);
				session.setAttribute("password", password);
				
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

		} catch (Exception e) {
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
