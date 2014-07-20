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

/*
 * So basically, we want this class to write the update to the database sort of like the
 * UpdateProfile class. 
 */

public class EditNote extends HttpServlet {

	private static final long serialVersionUID = -1126159043788240053L;

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
<<<<<<< HEAD
		
		DBConnectionManager dbCon = new DBConnectionManager();
		String url = dbCon.getUrl();
=======
		String url = "jdbc:postgres://gogimhfcpjxqrl:8aYEwVsOfS27mVbmexusgRQfNI@ec2-54-83-33-14.compute-1.amazonaws.com:5432/d5n7019odngdsm";
>>>>>>> 44dfab2dc13a8107610bd9803e5d74dd19c3ebf8
		
		HttpSession session = request.getSession(true);
		
		String editnote = request.getParameter("editnote");
		//int post_id = Integer.parseInt(request.getParameter("post_id")); // this feels dirty, but it works.
		int post_id = (Integer) session.getAttribute("post_id");
		int user_id = (Integer) session.getAttribute("user_id");

		
		String preppedQuery = 	"UPDATE notes SET content = ?"
								//+ " FROM accounts" // guess we don't need this..
								+ " WHERE notes.post_id = ?"
								+ " AND notes.user_id = ?;"; // where this is the accounts.user_id stored in session

		Connection con = null;
		PreparedStatement ps = null;

		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println(e.getMessage());
		}

		try {
			con = DriverManager.getConnection(url);

			System.out.println("\nEDITING/UPDATING NOTE!");
			System.out.println(preppedQuery); // DEBUG

			try {
				ps = con.prepareStatement(preppedQuery);

				ps.setString(1, editnote);
				ps.setInt(2, post_id);
				ps.setInt(3, user_id);
				
				// DEBUG OUTPUT FOR PREPAREDSTATEMENT VALUES:
				System.out.println("editnote: " + editnote);
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