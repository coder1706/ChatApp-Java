package com.kunal.chatapp.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import static com.kunal.chatapp.utils.ConfigReader.getValue;

public interface CommonDAO {
	public static Connection createConnection() throws ClassNotFoundException, SQLException {
		//Step-1 Load Driver
		Class.forName(getValue("DRIVER"));
		
		//Step-2 Make Connection 
		final String CONNECTION_STRING = getValue("CONNECTION_URL");
		String USER_ID = getValue("USERID");
		String PASSWORD = getValue("PASSWORD");
		Connection conn = DriverManager.getConnection(CONNECTION_STRING, USER_ID, PASSWORD);
		
		if(conn != null) {
			System.out.println("Connection Established with db...");
//			conn.close();
		}
		return conn;
	}
}
