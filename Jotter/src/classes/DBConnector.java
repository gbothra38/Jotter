package classes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnector {
	
	String jdbcURL= "jdbc:mysql://localhost:3306/jotter?autoReconnect=true&useSSL=false";
	String username="root";
	String password="root";
	Connection connection;
	public Connection makeConnection() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(jdbcURL, username, password);
		} catch (Exception e) {
			System.out.println(e);
		}
		if(connection!=null) {
			System.out.println("Connected");
		}
		return connection;
	}
	void closeConnection() {
		try {
			connection.close();
		} catch (SQLException e) {
			System.out.println(e);
		}
	}

}

//package classes;
//
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.SQLException;

//public class DBConnector {
//	
//	String jdbcURL= "jdbc:mysql://localhost:3306/jotter?autoReconnect=true&useSSL=false";
//	String username="root";
//	String password="root";
//	Connection connection;
//	public Connection makeConnection() {
//		try {
//			Class.forName("com.mysql.cj.jdbc.Driver");
//			connection = DriverManager.getConnection(jdbcURL, username, password);
//		} catch (Exception e) {
//			System.out.println(e);
//		}
//		if(connection!=null) {
//			System.out.println("Connected");
//		}
//		return connection;
//	}
//	void closeConnection() {
//		try {
//			connection.close();
//		} catch (SQLException e) {
//			System.out.println(e);
//		}
//	}
//
//}

