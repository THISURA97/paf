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

	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateCustomer(String customerData) {
		
		// Convert the input string to a JSON object
		JsonObject cusObject = new JsonParser().parse(customerData).getAsJsonObject();
		// Read the values from the JSON object
		String userID = cusObject.get("useID").getAsString();
		String firstName = cusObject.get("firstName").getAsString();
		String lastName = cusObject.get("lastName").getAsString();
		String dob = cusObject.get("dob").getAsString();
		String age = cusObject.get("age").getAsString();
		String gender = cusObject.get("gender").getAsString();
		String address = cusObject.get("address").getAsString();
		String phone = cusObject.get("phone").getAsString();
		String email = cusObject.get("email").getAsString();
		String username = cusObject.get("username").getAsString();
		String password = cusObject.get("password").getAsString();
		
		String output = cusObj.updateCustomer(userID, firstName, lastName, dob, age, gender, address, phone, email, username, password);
		
		return output;
	}
	
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteCustomer(String customerData)
	{
	//Convert the input string to an XML document
	Document doc = Jsoup.parse(customerData, "", Parser.xmlParser());
	//Read the value from the element <itemID>
	String userID = doc.select("userID").text();
	String output = cusObj.deleteCustomer(userID);
	
	return output;
	}
}