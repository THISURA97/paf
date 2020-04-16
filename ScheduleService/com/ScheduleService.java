package com;
import java.util.*;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import beans.ScheduleBean;
import model.Schedule;

@Path("/Schedule")


public class ScheduleService {
		
		Schedule schO = new Schedule();
		
		


		//View a Schedule
		
		@RolesAllowed({"administrator","doctor"})
		@GET
		@Path("/")
		@Produces(MediaType.APPLICATION_JSON)
		public List<ScheduleBean> viewSchedule(){

				return schO.viewSchedule();
		
		}
		
		@RolesAllowed({"administrator","doctor"})
		@POST
		@Path("/")
		@Consumes(MediaType.APPLICATION_JSON)
	    @Produces(MediaType.TEXT_PLAIN)
		public String insertSchedule(String Schedule) {

	        ScheduleBean schB = new ScheduleBean(Schedule);
	        String output =	schO.insertSchedule(schB);

	        return output;

	    }
		
				
		
		@RolesAllowed({"administrator","doctor"})
		@PUT
		@Path("/")
		@Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.TEXT_PLAIN)
		public String updateSchedule(String Schedule)
				{
			 ScheduleBean schB = new ScheduleBean(Schedule);
		        String output =	schO.updateSchedule(schB);

		        return output;

		}
		
		
		@RolesAllowed({"administrator","doctor"}) 
		
		//Remove a Schedule
		@DELETE
		@Path("/")
		@Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.TEXT_PLAIN)
		public String removeSchedule(String Schedule) {
			JsonObject schO = new JsonParser().parse(Schedule).getAsJsonObject();
	        
			String ScheduleID = schO.get("ScheduleID").getAsString();
			
			String output = schO.removeSchedule(ScheduleID);

	        return output;
		}
		
	    }

		
		

	

