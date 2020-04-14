package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	private static Connection connection;

	private DBConnection() {
	}

	public static Connection connect() throws SQLException, ClassNotFoundException {

		if (connection == null || connection.isClosed()) {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "");
		}
		return connection;
  
		
	}

}
