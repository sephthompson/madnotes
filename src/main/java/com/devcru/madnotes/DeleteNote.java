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

public class DeleteNote extends HttpServlet {

	private static final long serialVersionUID = -8903032598748156503L;

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String url = "jdbc:postgresql://localhost:5432/madnotes?user=postgres&password=pass1234";
		
		HttpSession session = request.getSession(true);
		
		int post_id = Integer.parseInt(request.getParameter("post_id")); // this feels dirty, but it works.
		int user_id = (Integer) session.getAttribute("user_id");
		
		// Referencing email is ineffective.  We must reference the user_id in a table-join.
		// This ensures that only the logged in user is allowed to delete their own note(s).
		// Task COMPLETED!
		
		String preppedQuery = 	"DELETE FROM notes USING accounts"
							+ " WHERE post_id = ?"
							+ " AND notes.user_id = ?";

		Connection con = null;
		PreparedStatement ps = null;

		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println(e.getMessage());
		}

		try {
			con = DriverManager.getConnection(url);

			System.out.println("\nDELETING NOTE!");
			System.out.println(preppedQuery); // DEBUG

			try {
				ps = con.prepareStatement(preppedQuery);

				ps.setInt(1, post_id);
				ps.setInt(2, user_id);
				
				// DEBUG OUTPUT FOR PREPAREDSTATEMENT VALUES:
				System.out.println("post_id: " + post_id);
				System.out.println("user_id: " + user_id);

				System.out.println("SQL=" + preppedQuery.toString()); // DEBUG

				ps.execute();

				System.out.println("Executed=" + preppedQuery.toString()); // DEBUG

				// RESPONSE TO CLIENT
				response.sendRedirect("notes.jsp");
				
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