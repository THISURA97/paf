package model;

import java.util.HashMap;

public interface Login {
	
	public HashMap<String, String> login(String email, String password, String roleId);
	public int getRegisterLoginId(String registerID);
	public String getRoleName(String roleID);
	public String resetPassword(String registerID, String currentPassword, String newPassword);
	public String verifyPassword(String registerID, String currentPassword);

}
