package model;

import java.util.HashMap;
import model.Login;

import model.User;

public class LoginImp implements Login{
	
	@Override
	public HashMap<String, String> login(String email, String password, String roleID) {
		HashMap<String, String> h = new HashMap<>();
		
		HashMap<String, String> result = User.login(email, password, roleID);
		
		if(result.get("status").equalsIgnoreCase("success")) {
			h.put("status", "success");
			h.put("userID", result.get("userID"));
		}else {
			h.put("status", "fail");
			h.put("userId", null);
		}
		
		return h;
		
	}
	
	@Override
	public int getLoginId(String userID) {
		int loginId = User.getLoginId(userID);
		return loginId;
	}
	
	@Override
	public String resetPassword (String userID, String currentPassword, String newPassword) {
		String status = User.resetPassword(userID, currentPassword, newPassword);
		return status;
	}
	
	
	public String verifyPassword(String User_Id, String currentPassword) {
		String status = User.verifyPassword(User_Id, currentPassword);
		return status;
	}
	
	@Override
	public String getRoleName(String roleId) {
		String roleName = User.getRoleName(roleId);
		return roleName;
	}
	
	
}



