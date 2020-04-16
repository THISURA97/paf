package model;

import java.util.HashMap;

public class LoginImp implements Login{
	
	
	public HashMap<String, String> login(String email, String password, String roleId) {
		HashMap<String, String> h = new HashMap<>();
		
		HashMap<String, String> result = Register.login(email, password, roleId);
		
		if(result.get("status").equalsIgnoreCase("success")) {
			h.put("status", "success");
			h.put("registerID", result.get("registerID"));
		}else {
			h.put("status", "fail");
			h.put("registerID", null);
		}
		
		return h;
		
	}
	
	
	public int getRegisterLoginId(String registerID) {
		int loginId = Register.getLoginId(registerID);
		return loginId;
	}
	
	
	public String resetPassword(String registerID, String currentPassword, String newPassword) {
		String status = Register.resetPassword(registerID, currentPassword, newPassword);
		return status;
	}
	
	
	public String verifyPassword(String registerID, String currentPassword) {
		String status = Register.verifyPassword(registerID, currentPassword);
		return status;
	}
	
	
	public String getRoleName(String roleId) {
		String roleName = Register.getRoleName(roleId);
		return roleName;
	}
	
}


}
