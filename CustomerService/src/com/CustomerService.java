package com;

import model.Customer;

//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
//For JSON
import com.google.gson.*;
//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

@Path("/Customers")
public class CustomerService {
	Customer cusObj = new Customer();

	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readCustomer() {
		return cusObj.readCustomer();
	}

	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertCustomer(@FormParam("firstName") String firstName, @FormParam("lastName") String lastName,
			@FormParam("dob") String dob, @FormParam("age") String age, @FormParam("gender") String gender,
			@FormParam("address") String address, @FormParam("phone") String phone, @FormParam("email") String email,
			@FormParam("username") String username, @FormParam("password") String password) {
		String output = cusObj.insertCustomer(firstName, lastName, dob, age, gender, address, phone, email, username,
				password);

		return output;
	}
}