package com;

import java.util.HashMap;
import beans.RegisterBeans;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/login")
public class LoginService {
	
LoginService loginServiceObj = new LoginServiceImp();
	
	@POST
	@Path("/login")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public String login(@FormParam("email") String email, @FormParam("password") String password, @FormParam("roleId") String roleId) {
		
		String registerID;
		
		HashMap<String, String> h = loginServiceObj.login(email, password, roleId);
		
		if(h.get("status").equalsIgnoreCase("success")) {
			registerID = h.get("registerID");
		}else {
			registerID = null;
		}
		
		return registerID;
	}
	
	@POST
	@Path("/getUserLoginId")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public String getUserLoginId(@FormParam("UserId") String User_Id) {
		int loginId = loginServiceObj.getUserLoginId(User_Id);
		return String.valueOf(loginId);
	}
	
	@POST
	@Path("/resetPassword")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String resetPassword(@FormParam("registerID") String registerID, @FormParam("currentPassword") String currentPassword, @FormParam("newPassword") String newPassword) {
		String status = loginServiceObj.resetPassword(registerID, currentPassword, newPassword);
		return status;
	}
	
	@POST
	@Path("/verifyPassword")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public String verifyPassword(@FormParam("registerID") String registerID, @FormParam("currentPassword") String currentPassword) {
		String status = loginServiceObj.verifyPassword(registerID, currentPassword);
		return status;
	}
	
	@POST
	@Path("/getRoleName")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public String getRoleName(@FormParam("roleID") String roleID) {
		String roleName = loginServiceObj.getRoleName(roleID);
		return roleName;
	}
	

}
