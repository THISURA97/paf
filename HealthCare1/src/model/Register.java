package model;

import beans.RegisterBeans;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

public class Register {
	
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

public static int registerUser(String userName, String email, String password, String phone ) {
	
	int i = 0;
	int j = 0;
	
	try {
		
		Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/healthcare", "root", "");
		
		String insertSQL1 = "INSERT INTO login VALUES(?, ?, ?, ?)";
		String insertSQL2 = "INSERT INTO register VALUES(?, ?, ?, ?)";
		String emailVarification = "SELECT * FROM login WHERE login_Email = ?";
		String getRId = "SELECT Login_Id FROM login WHERE Login_Email = ?";
		
		//prepared statements
		PreparedStatement stmt_emailVerification = con.prepareStatement(emailVarification);
		stmt_emailVerification.setString(1, email);
		
		ResultSet rs1 = stmt_emailVerification.executeQuery();
		
		if(rs1.first() == false) {
			
			PreparedStatement stmt_insertLogin = con.prepareStatement(insertSQL1);
			stmt_insertLogin.setInt(1, 0);
			stmt_insertLogin.setInt(2, 2);
			stmt_insertLogin.setString(3, email);
			stmt_insertLogin.setString(4, password);
			i = stmt_insertLogin.executeUpdate();
			
			PreparedStatement stmt_getRId = con.prepareStatement(getRId);
			stmt_getRId.setString(1, email);
			
			ResultSet rs2 = stmt_getRId.executeQuery();
			
			while (rs2.next()) {
				
				if (i > 0) {
					
					PreparedStatement stmt_insertRegister = con.prepareStatement(insertSQL2);
					stmt_insertRegister.setInt(1, 0);
					stmt_insertRegister.setInt(2, rs2.getInt(1));
					stmt_insertRegister.setString(3, userName);
					stmt_insertRegister.setString(4, phone);
					j= stmt_insertRegister.executeUpdate();
					
					if (j < 0)
						i = 0;
				}
			}
		} else {
			i = 0;
		}
		
	} catch (Exception e) {
		
		e.printStackTrace();
	}
	
	return i;
}

public static HashMap<String, String> login(String email, String password, String roleID) {
	HashMap<String, String> h = new HashMap<>();
	
	try {
		Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/healthcare", "root", "");
		
		String verifyLogin = "SELECT * FROM login WHERE L-email = ? and L_password = ?";
		PreparedStatement stmt_verifyLogin = con.prepareStatement(verifyLogin);
		stmt_verifyLogin.setString(1, email);
		stmt_verifyLogin.setString(2, password);
		
		ResultSet rs1_verifyLogin = stmt_verifyLogin.executeQuery();

		while (rs1_verifyLogin.next()) {

			if (rs1_verifyLogin.getInt(2) == Integer.parseInt(roleID)) {

				h.put("status", "success");
				h.put("registerId", register.getRegisterID());
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

public static int getLoginID(String registerID) {
	
	int loginID = 0;
	
	try {

		Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/healthcare", "root", "");
		String getLoginID = "SELECT login_id FROM user WHERE register_ID = " + registerID;
		PreparedStatement stmt_getLoginId = con.prepareStatement(getLoginID);
		ResultSet rs2_getLoginId = stmt_getLoginId.executeQuery();

		while (rs2_getLoginId.next()) {
			loginID = rs2_getLoginId.getInt(1);
		}

	} catch (Exception e) {
		e.printStackTrace();
	}
	return loginID;
}

public static String getRoleName(String roleID) {

	String roleName = null;

	try {

		Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/healthcare", "root", "");
		String getroleName = "SELECT roleName FROM role WHERE roleID = " + roleID;
		PreparedStatement stmt_getroleName = con.prepareStatement(getroleName);
		ResultSet rs3_getroleName = stmt_getroleName.executeQuery();

		while (rs3_getroleName.next()) {
			roleName = rs3_getroleName.getString(1);
		}

	} catch (Exception e) {
		e.printStackTrace();
	}

	return roleName;
}

public static String verifyPassword(String registerID, String currentPassword) {
	String status = null;

	try {
		Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/healthcare", "root", "");
		int loginID = getLoginID(registerID);
		String getLoginID = "SELECT * FROM login WHERE loginID = ? AND L_password = ?";

		PreparedStatement stmt_verifyPassword = con.prepareStatement(getLoginID);
		stmt_verifyPassword.setInt(1, loginID);
		stmt_verifyPassword.setString(2, currentPassword);

		ResultSet rs4_verifyPassword = stmt_verifyPassword.executeQuery();

		if (rs4_verifyPassword.first()) {
			status = "success";
		} else {
			status = "fail";
		}

	} catch (SQLException e) {
		e.printStackTrace();
	}

	return status;
}

public static String resetPassword(String registerID, String currentPassword, String newPassword) {

	String status = null;

	try {
		Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/healthcare", "root", "");
		int loginID = getLoginID(registerID);
		String passwordVerification = verifyPassword(registerID, currentPassword);

		if (passwordVerification.equalsIgnoreCase("success")) {
			String changePassword = "UPDATE login SET L_assword = " + newPassword;

			PreparedStatement stmt_changePassword = con.prepareStatement(changePassword);
			if (stmt_changePassword.executeUpdate() > 0) {
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


