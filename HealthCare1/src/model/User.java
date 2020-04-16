package model;

import beans.UserBeans;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;;

public class User {
	
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
	
	public String insertUser(UserBeans usr) {
		
		String output = "";
		
		try {
			
			Connection con = connect();
			
			if (con == null) {
				
				return "Database connection error while inserting details";
			}
			
			// prepared statements
			String userQuery = "inser into user" + "(userID, firstName, lastName, dob, age, gender, address, phone, email)" + "values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
			
			PreparedStatement preparedStmtUser = con.prepareStatement(userQuery);
			
			//Binding values for user Table
			preparedStmtUser.setInt(1, 0);
			preparedStmtUser.setString(2, usr.getFirstname());
			preparedStmtUser.setString(3, usr.getLaststname());
			preparedStmtUser.setString(4, usr.getDob());
			preparedStmtUser.setInt(5, Integer.parseInt(usr.getAge()));
			preparedStmtUser.setString(6, usr.getGender());
			preparedStmtUser.setString(7, usr.getAddress());
			preparedStmtUser.setString(8, usr.getPhone());
			preparedStmtUser.setString(9, usr.getEmail());
			
			//Execute the statement
			preparedStmtUser.execute();
			
			con.close();
			
			output = "Inserted successfully.";
			
		} catch (Exception e) {
			
			output = "Error while inserting details";
			System.err.println(e.getMessage());
		}
		
		return output;
	}
	
 	public List<UserBeans> readUser(){
 		
 		List<UserBeans> usrList = new ArrayList<>();
 		
 		String output = "";
 		
 		try {
 			
 			Connection con = connect();
 			
 			if (con == null) {
 				
 				System.out.println("Database Connection error while reading details");
 				return usrList;
 			}
 			
 			String query = "select u.userID, u.firstName, u.lastNmae, u.dob, u.age, u.gender, u.address, u.phone, u.email from user";
 			
 			Statement stmt = con.createStatement();
 			ResultSet rslt = stmt.executeQuery(query);
 			
 			while (rslt.next()) {
 				
 				UserBeans usr = new UserBeans(
 						
 						rslt.getInt("userID"),
 						rslt.getString("firstName"),
 						rslt.getString("laststName"),
 						rslt.getString("dob"),
 						rslt.getString("age"),
 						rslt.getString("gender"),
 						rslt.getString("address"),
 						rslt.getString("phone"),
 						rslt.getString("email")
 						
 				);
 				
 				usrList.add(usr);
 				
 			}
 			
 			con.close();

 		} catch (Exception e) {
 			
 			System.out.println("Error while reading details");
 			System.err.println(e.getMessage());
 		}
 		
 		return usrList;
 		
 	}
 	
 	public String updateUser(UserBeans usr) {
 		
 		String output = "";
 		
 		try {
 			
 			Connection con = connect();
 			
 			if (con == null) {
 				
 				return "Database connection error while updating the user details.";
 				
 			}
 			
 			//prepared statements
 			
 			String userQuery = "UPDATE user SET" + "firstName=?," + "lastName=?," + "dob=?," + "age=?," + "gender=?," + "phone=?," + "email=?" + "WHERE userID=?";
 			
 			PreparedStatement userDetails = con.prepareStatement(userQuery);
 			
 			//Binding values for userQuery
 			userDetails.setString(1, usr.getFirstname());
 			userDetails.setString(2, usr.getLaststname());
 			userDetails.setString(3, usr.getDob());
 			userDetails.setString(4, usr.getAge());
 			userDetails.setString(5, usr.getGender());
 			userDetails.setString(6, usr.getAddress());
 			userDetails.setString(7, usr.getPhone());
 			userDetails.setString(8, usr.getEmail());
 			userDetails.setInt(9, usr.getId());
 			
 			//Execute the statement
 			userDetails.execute();
 			
 			con.close();
 			
 			output = "User details updated successfully";
 		
 		} catch (Exception e) {
 			
 			output = "Error while updating details";
 			System.err.println(e.getMessage());
 		}
 		
 		return output;
 	
 	}
 	
 	public String deleteUser(String ID) {
 		
 		String output = "";
 		
 		try {
 			
 			Connection con = connect();
 			
 			if (con == null) {
 				
 				return "Database connection error while deleting details.";
 				
 			}
 			
 			// Prepared statements
 			String deleteUser = "delete from user where userID=?";
 			
 			PreparedStatement preparedStmtForUser = con.prepareStatement(deleteUser);
 			
 			//Binding values
 			preparedStmtForUser.setInt(1, Integer.parseInt(ID));
 			
 			//Executing statement
 			preparedStmtForUser.execute();
 			
 			con.close();
 			
 			output = "Deleted Successfully";
 		
 		} catch (Exception e) {
 			
 			output = "Error while deleting details";
 			System.err.println(e.getMessage());
 		}
 		
 		return output;
 	}
 	

		
}


