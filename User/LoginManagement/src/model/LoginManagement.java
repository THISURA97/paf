package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Base64;
import java.util.HashMap;

import org.json.JSONObject;

import com.google.gson.JsonObject;

public class LoginManagement {
	
	private static Connection connect() {
		
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
	
	public static JsonObject login(String email, String password) {
		
		JsonObject l = new JsonObject();

		try {
			Connection con = connect();

			String verifyLogin = "SELECT * FROM login WHERE L_email = ? and L_password = ?";
			PreparedStatement ps_verifyLogin = con.prepareStatement(verifyLogin);
			ps_verifyLogin.setString(1, email);
			ps_verifyLogin.setString(2, password);

			ResultSet rs_verifyLogin = ps_verifyLogin.executeQuery();
			if (rs_verifyLogin != null) {
				while (rs_verifyLogin.next()) {

					String emailColonPassword = "authKey" + email + ":" + password;
					String authString = Base64.getEncoder().encodeToString(emailColonPassword.getBytes());

					String getUrl = "http://localhost:8080/HealthCarePatientManagement/webapi/patient/loginId/"
							+ String.valueOf(rs_verifyLogin.getInt(1));
					JsonObject object = Response.getResponse(getUrl, authString);

					l.addProperty("status", "success");
					l.addProperty("userId", object.get("userId").getAsString());
					l.addProperty("authString", authString);
				}
			} else {
				l.addProperty("status", "fail");
				l.addProperty("userId", 0);
				l.addProperty("authString", "");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return l;
	}

	public static int getLoginId(String userId) {
		
		int loginId = 0;
		
		try {

			Connection con = connect();

			String getLoginId = "SELECT loginID FROM user WHERE userID = " + userId;
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

	public static String getRoleName(String roleId) {

		String roleName = "";

		try {

			Connection con =connect();

			String getroleName = "SELECT roleName FROM role WHERE roleID = " + roleId;
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

	public static String verifyPassword(String UserId, String currentPassword) {
		String status = "";

		try {

			Connection con = connect();

			int loginId = getLoginId(UserId);
			String getLoginId = "SELECT * FROM login WHERE loginID = ? AND L_password = ?";

			PreparedStatement ps_verifyPassword = con.prepareStatement(getLoginId);
			ps_verifyPassword.setInt(1, loginId);
			ps_verifyPassword.setString(2, currentPassword);

			ResultSet rs_verifyPassword = ps_verifyPassword.executeQuery();

			if (rs_verifyPassword.first()) {
				status = "success";
			} else {
				status = "fail";
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return status;
	}

	public static String resetPassword(String UserId, String currentPassword, String newPassword) {

		String status = "";

		try {
			Connection con = connect();
			int loginId = getLoginId(UserId);
			String passwordVerification = verifyPassword(UserId, currentPassword);

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

	public static JsonObject getAutization(String header) {
		JsonObject obj = new JsonObject();
		try {
			if (header != null) {
				Connection con = connect();
				byte[] decodedBytes = Base64.getDecoder().decode(header);
				String decodedString = new String(decodedBytes);

				if (decodedString.contains("authKey")) {

					String value = decodedString.replace("authKey", "");
					String[] values = value.split(":");

					String verifyLogin = "SELECT * FROM login WHERE L_email = ? and L_password = ?";
					String getRoleName = "SELECT * FROM role";
					PreparedStatement ps_verifyLogin = con.prepareStatement(verifyLogin);
					PreparedStatement ps_getRoleName = con.prepareStatement(getRoleName);
					ps_verifyLogin.setString(1, values[0]);
					ps_verifyLogin.setString(2, values[1]);

					ResultSet rs_verifyLogin = ps_verifyLogin.executeQuery();

					while (rs_verifyLogin.next()) {

						ResultSet rs_getRoleName = ps_getRoleName.executeQuery();
						while (rs_getRoleName.next()) {

							obj.addProperty("status", "authorized");
							obj.addProperty("role", rs_getRoleName.getString(2));

						}

					}

				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return obj;
	}


}
