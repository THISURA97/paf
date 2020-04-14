package com;

import java.util.List;

import javax.annotation.security.PermitAll;
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
@PermitAll

public class ScheduleService {
		
		Schedule objSchedule = new Schedule();


		//View a Schedule
		@GET
		@Path("/")
		@Produces(MediaType.APPLICATION_JSON)
		public List<ScheduleBean> viewSchedule() {
			return objSchedule.viewSchedule();
		}
				
		//Insert a Schedule
		@POST
		@Path("/")
		@Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.TEXT_PLAIN)
		public String insertSchedule(String ScheduleData) {
			
	 
			JsonObject scheduleObject = new JsonParser().parse(ScheduleData).getAsJsonObject();

			String ScheduleID = scheduleObject.get("s_is").getAsString();
			String ScheduleDate = scheduleObject.get("s_date").getAsString();
			String ScheduleTime = scheduleObject.get("s_time").getAsString();
			String ScheduleType = scheduleObject.get("s_type").getAsString();
			
					
			String output =	objSchedule.insertSchedule( ScheduleID, ScheduleDate, ScheduleTime, ScheduleType);
			return output;
		} 
				
		//Update a Schedule
		@PUT
		@Path("/")
		@Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.TEXT_PLAIN)
		public String updateDoctor(String Schedule) {
			ScheduleBean sch = new ScheduleBean(Schedule);
			
			String output =	objSchedule.updateSchedule(sch);
			return output;				
		}
				
		//Remove a Schedule
		@DELETE
		@Path("/")
		@Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.TEXT_PLAIN)
		public String removeSchedule(String schedule) {
			JsonObject ScheduleObject = new JsonParser().parse(schedule).getAsJsonObject();
			
			String ScheduleID = ScheduleObject.get("s_ID").getAsString();
			String output = objSchedule.removeSchedule(ScheduleID);
			
			return output;
			
			
		}

	}

