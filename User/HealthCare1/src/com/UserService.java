package com;

import javax.ws.rs.*;
import javax.ws.rs.core.*;

import beans.UserBeans;
import com.google.gson.*;
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.*;

import model.User;

import java.util.List;

@Path("/Users")
public class UserService{
	
	User userObj = new User();
	
	@GET
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	public List<UserBeans> readUser(){
		
		return userObj.readUser();
	}
	
	@POST
    @Path("/")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public String insertUser(String userData) {

        UserBeans usr = new UserBeans(userData);
        String output = userObj.insertUser(usr);

        return output;

    }
	
	@DELETE
    @Path("/")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public String deleteUser(String userData) {

        JsonObject userObject = new JsonParser().parse(userData).getAsJsonObject();
        String userID = userObject.get("userID").getAsString();

        String output = userObj.deleteUser(userID);

        return output;

    }
	
}