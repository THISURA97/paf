package com;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.parser.Parser;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import model.Item;


@Path("/Items")

public class itemService {
	Item itemObj = new Item();
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readItems()
	 {
		return itemObj.readItems(); 
	 }
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertItem(@FormParam("scheduleDate") String scheduleDate,
	 @FormParam("scheduleTime") String scheduleTime,
	 @FormParam("scheduleType") String scheduleType)
	{
	 String output = itemObj.insertItem(scheduleDate, scheduleTime, scheduleType);
	return output;
	}
	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateItem(String itemData)
	{
	//Convert the input string to a JSON object
	 JsonObject itemObject = new JsonParser().parse(itemData).getAsJsonObject();
	//Read the values from the JSON object
	 String scheduleID = itemObject.get("scheduleID").getAsString();
	 String scheduleDate = itemObject.get("scheduleDate").getAsString();
	 String scheduleTime = itemObject.get("scheduleTime").getAsString();
	 String scheduleType = itemObject.get("scheduleType").getAsString();
	 String output = itemObj.updateItem(scheduleID, scheduleDate, scheduleTime, scheduleType);
	return output;
	}
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteItem(String itemData)
	{
	//Convert the input string to an XML document
	 Document doc = Jsoup.parse(itemData, "", Parser.xmlParser());

	//Read the value from the element <itemID>
	 String scheduleID = doc.select("scheduleID").text();
	 String output = itemObj.deleteItem(scheduleID);
	return output;
	}

}
