package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Customer {
		
	private Connection connect() {
		Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			 //Provide the correct details: DBServer/DBName, username, password
			 con = DriverManager.getConnection("Jdbc:mysql://localhost:3306/healthcare?serverTimezone=UTC", "root", ""); 
		}catch (Exception e) {
			{
				e.printStackTrace();
			}
		}
		
		return con;
	}
	
	public String insertCustomer(String FirstName, String LastName, String DOB,  String Age,  String Gender,  String Address,  String Phone,  String Email,  String UserName,  String Password ) {
		
		String output = "";
		
		try {
			
			Connection con = connect();
			if (con == null) {
				
				return "Error while connecting to the database for inserting";
			}
			
			// prepared statements
			String query = "insert into user (`userID`,`firstName`,`lastName`,`dob`,`age`, `gender`, `address`, `phone`,`email`,`username`, `password`)" 
										+ "values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			
			PreparedStatement preparedStmt = con.prepareStatement(query);
			
			//Binding values for user Table
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, FirstName);
			preparedStmt.setString(3, LastName);
			preparedStmt.setString(4, DOB);
			preparedStmt.setInt(5, Integer.parseInt(Age));
			preparedStmt.setString(6, Gender);
			preparedStmt.setString(7, Address);
			preparedStmt.setString(8, Phone);
			preparedStmt.setString(9, Email);
			preparedStmt.setString(10, UserName);
			preparedStmt.setString(11, Password);
			
			//Execute the statement
			preparedStmt.execute();
			
			con.close();
			
			output = "Inserted successfully.";
			
		} catch (Exception e) {
			
			output = "Error while inserting details";
			System.err.println(e.getMessage());
		}
		
		return output;
	}
	
 	public String readCustomer() {
 		
 		String output = "";
 		
 		try {
 			
 			Connection con = connect();
 			
 			if (con == null)
 			{
 				return "error while connecting to the database for reading";
 			}
 			
 			//prepare the html table to be displayed
 			
 			output = " <link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css\">\r\n" + 
 					 "  <script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js\"></script>\r\n" + 
 					 "  <script src=\"https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js\"></script>\r\n" + 
 					 "  <script src=\"https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js\"></script>"
 					 +" <div class=\"container\">"
 					 +"<h2>Schedule Management</h2>"
 					 +"<table class=\"table\">"
 					 +"<thead class=\"thead-dark\">"
 					 + "<tr>"
 					 + "<th>firstName</th>" 
 					 + "<th>lastName</th>"
 					 + "<th>dob</th>"
 					 + "<th>age</th>"
 					 + "<th>gender</th>"
 					+ "<th>address</th>"
 					+ "<th>phone</th>"
 					+ "<th>email</th>"
 					+ "<th>username</th>"
 					+ "<th>password</th>"
 					 + "</tr>"
 					 +"<thead>"
 					 +"</div>";
 			String query = "select * from user";
 			Statement stmt = con.createStatement();
 			ResultSet rs = stmt.executeQuery(query);
 			
 			//iterate through the rows in the result set 
 			while (rs.next())
 			{
 				String userID = Integer.toString(rs.getInt("userID"));
 				String firstName = rs.getString("firstName");
 				String lastName = rs.getString("lastName");
 				String dob = rs.getString("dob");
 				String age = Integer.toString(rs.getInt("age"));
 				String gender = rs.getString("gender");
 				String address = rs.getString("address");
 				String phone = rs.getString("phone");
 				String email = rs.getString("email");
 				String username = rs.getString("username");
 				String password = rs.getString("password");
 				
 				// Add into the html table
 				
 				output += "<tr><td>" + firstName + "</td>";
 				output += "<td>" + lastName + "</td>";
 				output += "<td>" + dob + "</td>";
 				output += "<td>" + age + "</td>";
 				output += "<td>" + gender + "</td>";
 				output += "<td>" + address + "</td>";
 				output += "<td>" + phone + "</td>";
 				output += "<td>" + email + "</td>";
 				output += "<td>" + username + "</td>";
 				output += "<td>" + password + "</td>";
 				
 			}
 			
 			con.close();
 			
 			// Complete the html table
 			output += "</table>";
 			
 		} catch (Exception e) {
 			
 			output = "Error while reading the details.";
 			System.err.println(e.getMessage());
 		}
 		
 		return output;
 	}
 	
 	public String updateCustomer(String UserID, String FirstName, String LastName, String DOB,  String Age,  String Gender,  String Address,  String Phone,  String Email,  String Username,  String Password ) {
 		
 		String output = "";
 		
 		try {
 			
 			Connection con = connect();
 			
 			if (con == null) {
 				
 				return "Database connection error while updating the user details.";
 				
 			}
 			
 			//prepared statements
 			
 			String query = "UPDATE user SET" + "firstName=?," + "lastName=?," + "dob=?," + "age=?," + "gender=?," + "phone=?," + "email=?," + "username," + "password" + "WHERE userID=?";
 			
 			PreparedStatement preparedStmt = con.prepareStatement(query);
 			
 			//Binding values 
 			preparedStmt.setString(1, FirstName);
 			preparedStmt.setString(2, LastName);
 			preparedStmt.setString(3, DOB);
 			preparedStmt.setInt(4, Integer.parseInt(Age));
			preparedStmt.setString(5, Gender);
			preparedStmt.setString(6, Address);
			preparedStmt.setString(7, Phone);
			preparedStmt.setString(8, Email);
			preparedStmt.setString(9, Username);
			preparedStmt.setString(10, Password);
 			preparedStmt.setInt(11, Integer.parseInt(UserID));
 			
 			//Execute the statement
 			preparedStmt.execute();
 			
 			con.close();
 			
 			output = "User details updated successfully";
 		
 		} catch (Exception e) {
 			
 			output = "Error while updating details";
 			System.err.println(e.getMessage());
 		}
 		
 		return output;
 	
 	}
 	
 	public String deleteCustomer(String userID) {
 		
 		String output = "";
 		
 		try {
 			
 			Connection con = connect();
 			
 			if (con == null) {
 				
 				return "Database connection error while deleting details.";
 				
 			}
 			
 			// Prepared statements
 			String query = "delete from user where userID=?";
 			
 			PreparedStatement preparedStmt = con.prepareStatement(query);
 			
 			//Binding values
 			preparedStmt.setInt(1, Integer.parseInt(userID));
 			
 			//Executing statement
 			preparedStmt.execute();
 			
 			con.close();
 			
 			output = "Deleted Successfully";
 		
 		} catch (Exception e) {
 			
 			output = "Error while deleting details";
 			System.err.println(e.getMessage());
 		}
 		
 		return output;
 	}
 	
}

