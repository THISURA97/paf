package model;

import java.util.HashMap;

public interface Login {
	
	public HashMap<String, String> login(String email, String password, String roleId);
	public int getLoginId(String userID);
	public String getRoleName(String roleID);
	public String resetPassword(String userID, String currentPassword, String newPassword);
	public String verifyPassword(String userID, String currentPassword);

}
