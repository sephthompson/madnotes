package com.devcru.madnotes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnectionManager {

	// CURRENTLY NOT IN USE.  TO BE ADDED IN A LATER REFACTOR.
	
	private Connection con;
	
	String url = "jdbc:postgresql://localhost:5432/madnotes?user=postgres&password=pass1234";
	
	public DBConnectionManager(String url) throws ClassNotFoundException, SQLException{
		Class.forName("org.postgresql.Driver");
		this.con = DriverManager.getConnection(url);
	}
	
	public Connection getConnection(){
		return this.con;
	}
}