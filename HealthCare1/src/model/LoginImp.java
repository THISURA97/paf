package model;

import java.util.HashMap;
import model.Login;

public class LoginImp implements Login{
	
	@Override
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
	
	@Override
	public String resetPassword(String registerID, String currentPassword, String newPassword) {
		String status = Register.resetPassword(registerID, currentPassword, newPassword);
		return status;
	}
	
	@Override
	public String verifyPassword(String registerID, String currentPassword) {
		String status = Register.verifyPassword(registerID, currentPassword);
		return status;
	}
	
	@Override
	public String getRoleName(String roleId) {
		String roleName = Register.getRoleName(roleId);
		return roleName;
	}

	@Override
	public int getRegisterLoginId(String registerID) {
		// TODO Auto-generated method stub
		return 0;
	}
	
}



