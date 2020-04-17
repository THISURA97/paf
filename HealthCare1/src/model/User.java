package model;

import beans.UserBeans;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import beans.UserBeans;

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
			String userQuery = "inser into user" + "(userID, firstName, lastName, dob, age, gender, address, phone, email, username, password)" + "values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			
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
			preparedStmtUser.setString(10, usr.getUsername());
			preparedStmtUser.setString(9, usr.getPassword());
			
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
 			
 			String query = "select u.userID, u.firstName, u.lastNmae, u.dob, u.age, u.gender, u.address, u.phone, u.email, u.username, u.password from user";
 			
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
 						rslt.getString("email"),
 						rslt.getString("username"),
 						rslt.getString("password")
 						
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
 			
 			String userQuery = "UPDATE user SET" + "firstName=?," + "lastName=?," + "dob=?," + "age=?," + "gender=?," + "phone=?," + "email=?," + "username," + "password" + "WHERE userID=?";
 			
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
 			userDetails.setString(8, usr.getUsername());
 			userDetails.setString(8, usr.getPassword());
 			userDetails.setInt(9, usr.getUserID());
 			
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
 	
 	
 	//login
 	public static HashMap<String, String> login(String email, String password, String roleID) {
		HashMap<String, String> h = new HashMap<>();

		try {
			Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/healthcare", "root", "");

			String verifyLogin = "SELECT * FROM login WHERE L_email = ? and L_password = ?";
			PreparedStatement ps_verifyLogin = con.prepareStatement(verifyLogin);
			ps_verifyLogin.setString(1, email);
			ps_verifyLogin.setString(2, password);

			ResultSet rs_verifyLogin = ps_verifyLogin.executeQuery();

			while (rs_verifyLogin.next()) {

				if (rs_verifyLogin.getInt(2) == Integer.parseInt(roleID)) {

					UserBeans User = getUserDetailsByLoginId(String.valueOf(rs_verifyLogin.getInt(1)));
					h.put("status", "success");
					//h.put("userID", User.getUserID());
				}else {
					h.put("status", "fail");
					h.put("userId", null);
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return h;
	}

	public static int getLoginId(String userID) {
		
		int loginId = 0;
		
		try {

			Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/healthcare", "root", "");
			String getLoginId = "SELECT loginID FROM user WHERE userID = " + userID;
			PreparedStatement ps_getLoginId = con.prepareStatement(getLoginId);
			ResultSet rs_getLoginId = ps_getLoginId.executeQuery();

			while (rs_getLoginId.next()) {
				loginId = rs_getLoginId.getInt(1);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return loginId;
	}

	public static String getRoleName(String roleID) {

		String roleName = null;

		try {

			Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/healthcare", "root", "");
			String getroleName = "SELECT roleName FROM role WHERE roleID = " + roleID;
			PreparedStatement ps_getroleName = con.prepareStatement(getroleName);
			ResultSet rs_getroleName = ps_getroleName.executeQuery();

			while (rs_getroleName.next()) {
				roleName = rs_getroleName.getString(1);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return roleName;
	}

	public static String verifyPassword(String UserID, String currentPassword) {
		String status = null;

		try {
			Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/healthcare", "root", "");
			int loginId = getLoginId(UserID);
			String getLoginId = "SELECT * FROM login WHERE logiID = ? AND L_password = ?";

			PreparedStatement ps_verifyPassword = con.prepareStatement(getLoginId);
			ps_verifyPassword.setInt(1, loginId);
			ps_verifyPassword.setString(2, currentPassword);

			ResultSet rs_verifyPassword = ps_verifyPassword.executeQuery();

			if (rs_verifyPassword.first()) {
				status = "success";
			} else {
				status = "fail";
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return status;
	}

	public static UserBeans getUserDetailsByLoginId(String loginID) {
		
		UserBeans User = null;
		
		try {

			Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/healthcare", "root", "");

			String getSql = "SELECT u.userID, u.firstName, u.lastName, u.dob, u.age, u.gender, u.address, u.phone, u.email FROM user u, login l WHERE u.loginID = ? AND u.loginID = l.loginID";

			PreparedStatement ps_getUserDetails = con.prepareStatement(getSql);
			ps_getUserDetails.setInt(1, Integer.parseInt(loginID));

			ResultSet rs = ps_getUserDetails.executeQuery();

			while (rs.next()) {

				User = new UserBeans();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return User;
	}

	public static String resetPassword(String UserID, String currentPassword, String newPassword) {

		String status = null;

		try {
			Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/healthcare", "root", "");
			int loginID = getLoginId(UserID);
			String passwordVerification = verifyPassword(UserID, currentPassword);

			if (passwordVerification.equalsIgnoreCase("success")) {
				String changePassword = "UPDATE login SET L_password = " + newPassword;

				PreparedStatement ps_changePassword = con.prepareStatement(changePassword);
				if (ps_changePassword.executeUpdate() > 0) {
					status = "success";
				} else {
					status = "fail";
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return status;
	}
 	

		
}


