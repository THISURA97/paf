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
			
			//DB name, username, password
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/healthcare", "root", "");
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		return con;
	}
	
	public String insertCustomer(String firstName, String lastName, String dob,  String age,  String gender,  String address,  String phone,  String email,  String username,  String password ) {
		
		String output = "";
		
		try {
			
			Connection con = connect();
			
			if (con == null) {
				
				return "Error while connecting to the database for inserting";
			}
			
			// prepared statements
			String query = "inser into user" + "(userID, firstName, lastName, dob, age, gender, address, phone, email, username, password)" + "values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			
			PreparedStatement preparedStmt = con.prepareStatement(query);
			
			//Binding values for user Table
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, firstName);
			preparedStmt.setString(3, lastName);
			preparedStmt.setString(4, dob);
			preparedStmt.setInt(5, Integer.parseInt(age));
			preparedStmt.setString(6, gender);
			preparedStmt.setString(7, address);
			preparedStmt.setString(8, phone);
			preparedStmt.setString(9, email);
			preparedStmt.setString(10, username);
			preparedStmt.setString(9, password);
			
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
 			
 			output = "<table border=\"1\"><tr><th> firstName </th><th> lastName </th><th> dob </th><th> age </th><th> gender </th><th> address </th><th> phone </th><th> email </th><th> username </th><th> password </th></tr> ";
 			
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
 				
 				output += "<tr><td>" + firstName + "/td>";
 				output += "<td>" + lastName + "/td>";
 				output += "<td>" + dob + "/td>";
 				output += "<td>" + age + "/td>";
 				output += "<td>" + gender + "/td>";
 				output += "<td>" + address + "/td>";
 				output += "<td>" + phone + "/td>";
 				output += "<td>" + email + "/td>";
 				output += "<td>" + username + "/td>";
 				output += "<td>" + password + "/td>";
 				
 				//buttons
 				output += "<td><input name= \"btnUpdate\" type=|\"button\"value=\"Update\" class=\"btn btn-secondary\"></td>"
 						+ "<td><form method=\"post\" action=\"customer.jsp\">"
 						+ "<input name=\"btnRemove\" type=\"submit\" value=\"Remove\"class=\"btn btn-danger\">"
 						+ "<input name=\"userID\" type=\"hidden\" value=\"" + userID
 						+ "\">" + "</form></td></tr>";
 				
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
 	
 	public String updateCustomer(String userID, String firstName, String lastName, String dob,  String age,  String gender,  String address,  String phone,  String email,  String username,  String password ) {
 		
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
 			preparedStmt.setString(1, firstName);
 			preparedStmt.setString(2, lastName);
 			preparedStmt.setString(3, dob);
 			preparedStmt.setInt(5, Integer.parseInt(age));
			preparedStmt.setString(6, gender);
			preparedStmt.setString(7, address);
			preparedStmt.setString(8, phone);
			preparedStmt.setString(9, email);
			preparedStmt.setString(10, username);
			preparedStmt.setString(9, password);
 			preparedStmt.setInt(9, Integer.parseInt(userID));
 			
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
