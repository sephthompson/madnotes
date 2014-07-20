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

public class ShowContent extends HttpServlet {

	private static final long serialVersionUID = 6736266448070943215L;

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		DBConnectionManager dbCon = new DBConnectionManager();
		String url = dbCon.getUrl();
		
		HttpSession session = request.getSession(true);

		String date = request.getParameter("post_date");
		
		int post_id = Integer.parseInt(request.getParameter("post_id")); // this feels dirty, but it works.
		int user_id = (Integer) session.getAttribute("user_id");

		String preppedQuery = "SELECT content FROM notes"
				+ " WHERE notes.post_id = ?"
				+ " AND notes.user_id = ?;"; // where this is the accounts.user_id stored in session

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
			System.out.println("\nFETCHING POST CONTENT!");
			System.out.println(preppedQuery); // DEBUG
			try {
				ps = con.prepareStatement(preppedQuery);

				ps.setInt(1, post_id);
				ps.setInt(2, user_id);
				
				// DEBUG OUTPUT FOR PREPAREDSTATEMENT VALUES:
				System.out.println("postdate: " + date);
				System.out.println("post_id: " + post_id);
				System.out.println("user_id: " + user_id);

				System.out.println("SQL=" + preppedQuery.toString()); // DEBUG

				rs = ps.executeQuery();
				
				System.out.println("Executed=" + preppedQuery.toString()); // DEBUG

				boolean isEmpty = rs.next();
				if (!isEmpty) {
					response.sendRedirect("error.jsp");
				} else if (isEmpty) {
					session.setAttribute("post_date", date);
					session.setAttribute("post_id", post_id);
					session.setAttribute("content", rs.getString("content"));

					response.sendRedirect("editnotes.jsp");
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