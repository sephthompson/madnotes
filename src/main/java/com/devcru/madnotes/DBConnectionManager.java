package com.devcru.madnotes;

public class DBConnectionManager {
	
	// For now, we can just use this as a reference class for the URL.
	
	// localhost
	//private String url = "jdbc:postgresql://localhost:5432/madnotes?user=postgres&password=pass1234";
	
	//Heroku
	private String url = "jdbc:postgres://gogimhfcpjxqrl:8aYEwVsOfS27mVbmexusgRQfNI@ec2-54-83-33-14.compute-1.amazonaws.com:5432/d5n7019odngdsm";

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
}