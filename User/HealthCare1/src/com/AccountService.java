package com;

import model.Account;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
//For REST Service import javax.ws.rs.*; 
import javax.ws.rs.core.MediaType;

//For JSON 
import com.google.gson.*;

//For XML import org.jsoup.*; import org.jsoup.parser.*; import org.jsoup.nodes.Document; 

@Path("/Accounts")
public class AccountService {
	Account accObj = new Account();

	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readAccount() {
		return accObj.readAccount();
	}

	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertItem(@FormParam("title") String title,
			@FormParam("fname") String fname,
			@FormParam("lname") String lname,
			@FormParam("nic") String nic,
			@FormParam("dob") String dob,
			@FormParam("address") String address,
			@FormParam("phone") String phone,
			@FormParam("email") String email) 
	{
		String output = accObj.insertAccount(title, fname, lname, nic, dob, address, phone, email);
		return output;
	}

}
