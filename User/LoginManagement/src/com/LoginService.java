package com;

import model.LoginManagement;
import model.Response;
import java.util.HashMap;

import javax.annotation.security.PermitAll;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.gson.JsonObject;

@Path("/login")
public class LoginService {
	
	LoginManagement loginObj = new LoginManagement();
	
	@POST
	@Path("/login")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public Response login(@FormParam("email") String email, @FormParam("password") String password) {	
		JsonObject obj = loginObj.login(email, password);
		return Response.ok(obj.toString()).build();
	}
	
	@GET
	@Path("/getUserLoginId/{UserId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getUserLoginId(@PathParam("userId") String User_Id, @HeaderParam("authString") String authString) {
		int loginId = loginObj.getLoginId(User_Id);
		return Response.ok(String.valueOf(loginId)).build();
	}
	
	@POST
	@Path("/resetPassword")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public Response resetPassword(@FormParam("userId") String User_Id, @FormParam("currentPassword") String currentPassword, @FormParam("newPassword") String newPassword, @HeaderParam("authString") String authString) {
		String status = loginObj.resetPassword(User_Id, currentPassword, newPassword);
		return Response.ok(status).build();
	}
	
	@POST
	@Path("/verifyPassword")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public Response verifyPassword(@FormParam("userId") String User_Id, @FormParam("currentPassword") String currentPassword, @HeaderParam("authString") String authString) {
		String status = loginObj.verifyPassword(User_Id, currentPassword);
		return Response.ok(status).build();
	}
	
	@GET
	@Path("/getRoleName/{roleId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getRoleName(@PathParam("roleId") String roleId, @HeaderParam("authString") String authString) {
		String roleName = loginObj.getRoleName(roleId);
		return Response.ok(roleName).build();
	}
	
	@GET
	@Path("/authorizeUser")
	@Produces(MediaType.APPLICATION_JSON)
	public Response authorizeUser(@HeaderParam("authString") String authString) {
		JsonObject obj = loginObj.getAutization(authString);
		return Response.ok(obj.toString()).build();
	}

}
