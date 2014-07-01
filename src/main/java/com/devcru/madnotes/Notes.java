package com.devcru.madnotes;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Notes extends HttpServlet {

	private static final long serialVersionUID = 6736266448070943215L;

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		Connection con = null;
		try {
			con = DriverManager
					.getConnection("jdbc:postgresql://localhost:5432/madnotes?user=postgres&password=pass1234");
		} catch (Exception e) {
			e.printStackTrace(System.out);
		}
		
		PrintWriter out = response.getWriter();

		PreparedStatement ps = null;
		ResultSet rs = null;
		String SQL = "";
		String email = request.getParameter("email");

		try {
			int postCount = 0;
			SQL = "SELECT post_id, post_date, content FROM accounts, notes WHERE accounts.user_id = notes.user_id AND accounts.email = ?;";
			ps = con.prepareStatement(SQL);
			ps.setString(1, email);
			rs = ps.executeQuery();
			while (rs.next()) {
				postCount++;
				
				out.print("<tr>"
						+ "<td>" + rs.getString("post_id") + "</td>"
						+ "<td>" + rs.getString("post_date") + "</td>"
						+ "<td>" + rs.getString("post_id") + "</td>"
						+ "</tr>");
				out.flush();
				out.close();
				
			}
		} catch (SQLException e) {
			e.printStackTrace(System.out);
		} finally {
			try {
				ps.close();
			} catch (Exception e) {
			}
			try {
				rs.close();
			} catch (Exception e) {
			}
			try {
				if (null != con) {
					con.close();
				}
			} catch (SQLException ex) {
				ex.printStackTrace(System.out);
			}
		}
	}
}