package com;

import com.google.gson.JsonObject;
import model.Response;

public class AccessFilter {
	
	public static boolean checkAccess(String[] giveAccess, String authString) {

		boolean status = false;

		if (authString != null) {
			JsonObject obj = Response.getResponse(
					"http://localhost:8080/HealthCareLoginManagement/login/login/authorizeUser/", authString);
			for (String access : giveAccess) {

				String role = obj.get("role").getAsString();

				if (role.equalsIgnoreCase(access)) {
					status = true;
				}

			}
		}
		return status;
	}

}
