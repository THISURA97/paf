package util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
	private static Connection connection;
	private static String url;
	private static String username;
	private static String password;
	private DBConnection() {
		
	}
	
	public static Connection getConnection() {
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			url = "jdbc:mysql://127.0.0.1:3306/test?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
			username = "root";
			password = "sliit123";
			if (connection == null || connection.isClosed()) {
				 connection = DriverManager.getConnection(url, username, password);
				}
				
		}catch(Exception e){
			e.printStackTrace();
		}
		return connection;
	}
}
