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

public class AddNote extends HttpServlet {

	private static final long serialVersionUID = -6914155446699694977L;

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		DBConnectionManager dbCon = new DBConnectionManager();
		String url = dbCon.getUrl();
		
		// We will need to pull user_id from the session.
		// Task COMPLETED!
		
		HttpSession session = request.getSession(true);
		
		String addnote = request.getParameter("addnote");
		int user_id = (Integer) session.getAttribute("user_id");

		// Referencing email is ineffective.  We need to reference the user_id here.
		// This query will need to be rewritten and tested.
		// Task COMPLETED!
		
		String preppedQuery = 	"INSERT INTO notes (user_id, title)"
							+ " VALUES (?, ?)";

		Connection con = null;
		PreparedStatement ps = null;

		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println(e.getMessage());
		}

		try {
			con = DriverManager.getConnection(url);

			System.out.println("\nADDING NOTE!");
			System.out.println(preppedQuery); // DEBUG

			try {
				ps = con.prepareStatement(preppedQuery);

				ps.setInt(1, user_id);
				ps.setString(2, addnote);
				
				// DEBUG OUTPUT FOR PREPAREDSTATEMENT VALUES:
				System.out.println("user_id: " + user_id);
				System.out.println("addnote: " + addnote);

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