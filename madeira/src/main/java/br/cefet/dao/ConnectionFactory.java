package br.cefet.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
	
	final static String HOSTNAME = "localhost";
	final static int PORT = 3306;
	final static String BD = "bd_moveis";
	final static String USER = "root";
	final static String PASSWORD = "";

	public static Connection getConnection() {
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			return DriverManager.getConnection("jdbc:mysql://" + HOSTNAME + ":" + PORT +"/" + BD, 
					USER, 
					PASSWORD);
		} catch(SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}
}
