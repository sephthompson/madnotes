package com.devcru.madnotes;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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

		String url = "jdbc:postgresql://localhost:5432/madnotes?user=postgres&password=pass1234";

		String email = request.getParameter("email");
		String password = request.getParameter("password");

		String preppedQuery = "SELECT * FROM accounts, profiles"
				+ " WHERE email = ? AND password = ?"
				+ " AND accounts.user_id = profiles.user_id;";

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println(e.getMessage());
		}

		try {
			con = DriverManager.getConnection(url);
			try {
				ps = con.prepareStatement(preppedQuery);

				ps.setString(1, email);
				ps.setString(2, password);

				rs = ps.executeQuery();

				boolean isEmpty = rs.next();
				if (!isEmpty) {
					response.sendRedirect("loginfailure.jsp");
				} else if (isEmpty) {

					HttpSession session = request.getSession(true);
					session.setAttribute("user_id", rs.getInt("user_id"));
					session.setAttribute("email", rs.getString("email"));
					session.setAttribute("firstname", rs.getString("firstname"));
					session.setAttribute("lastname", rs.getString("lastname"));
					session.setAttribute("about", rs.getString("about"));

					response.sendRedirect("loginsuccess.jsp");
					return;
				}
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
		}
	}

}
