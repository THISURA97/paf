package com;

import java.util.List;

import javax.annotation.security.PermitAll;
import javax.websocket.server.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import beans.ScheduleBean;
import model.Schedule;

@Path("/Schedule")


public class ScheduleService {
		
		Schedule sch = new Schedule();


		//View a Schedule
		@PermitAll
		//@RolesAllowed({"administrator","doctor"}) - access control
		@GET
		@Path("/")
		@Produces(MediaType.APPLICATION_JSON)
		public List<ScheduleBean> viewSchedule() {
			return sch.viewSchedule();
		}
		
		@PermitAll
		@GET
		@Path("/{ScheduleID}")
		@Produces(MediaType.APPLICATION_JSON)
		public ScheduleBean viewScheduleById(@PathParam("PatientID") int id) {
			return sch.viewScheduleById(id);
		}
		
		@PermitAll
		//@RolesAllowed({"administrator","doctor"}) - access control
		
		//Insert a Schedule
		@POST
		@Path("/")
		@Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.TEXT_PLAIN)
		public String insertSchedule(String ScheduleData) {
			
	 
			ScheduleBean schedule = new ScheduleBean(ScheduleData);
			return sch.insertSchedule(schedule);
			
		} 
			
		@PermitAll
		//@RolesAllowed({"administrator","doctor"}) - access control
		
		//Update a Schedule
		@PUT
		@Path("/")
		@Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.TEXT_PLAIN)
		public String updateSchedule(String ScheduleData) {
			
			ScheduleBean schB = new ScheduleBean(ScheduleData);
			return sch.updateSchedule(schB);
						
		}
		
		@PermitAll
		//@RolesAllowed({"administrator","doctor"}) - access control
		
		//Remove a Schedule
		@DELETE
		@Path("/")
		@Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.TEXT_PLAIN)
		public String removeSchedule(String ScheduleData) {
			JsonObject ScheduleObject = new JsonParser().parse(ScheduleData).getAsJsonObject();
			
			String ScheduleID = ScheduleObject.get("ScheduleID").getAsString();
			return sch.removeSchedule(ScheduleID);
			
			
		}

	}

