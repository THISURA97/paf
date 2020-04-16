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
		String getUId = "SELECT Login_Id FROM login WHERE Login_Email = ?";
		
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
			
			PreparedStatement stmt_getUId = con.prepareStatement(getUId);
			stmt_getUId.setString(1, email);
			
			ResultSet rs2 = stmt_getUId.executeQuery();
			
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

}
