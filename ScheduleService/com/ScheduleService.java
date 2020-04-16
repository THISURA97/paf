package com;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import beans.ScheduleBean;
import model.Schedule;

@Path("/Schedule")


public class ScheduleService {
		
		Schedule schO = new Schedule();
		
		ScheduleBean sb;


		//View a Schedule
		
		@RolesAllowed({"administrator","doctor"})
		@GET
		@Path("/view")
		@Produces(MediaType.APPLICATION_JSON)
		public String viewSchedule(){

				return schO.viewSchedule();
		
		}
		
		@RolesAllowed({"administrator","doctor"})
		@POST
		@Path("/insert")
		@Consumes(MediaType.APPLICATION_JSON)
	    @Produces(MediaType.TEXT_PLAIN)
		public String insertSchedule(String ScheduleData) {

	        sb = new ScheduleBean();
	        sb.convertStringToJSONInsert(ScheduleData);
	        String output =	schO.insertSchedule(sb);

	        return output;

	    }
		
				
		
		@RolesAllowed({"administrator","doctor"})
		@PUT
		@Path("/update")
		@Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.TEXT_PLAIN)
		public String updateSchedule(String ScheduleData)
				{
			 sb = new ScheduleBean();
		        sb.convertStringToJSONUpdate(ScheduleData);
		        String output =	schO.updateSchedule(sb);

		        return output;

		}
		
		
		//@RolesAllowed({"administrator","doctor"}) 
		
		//Remove a Schedule
		@DELETE
		@Path("/delete")
		@Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.TEXT_PLAIN)
		public String removeSchedule(String ScheduleData) {
			 
			
			 sb = new ScheduleBean();
		        sb.convertStringToJSONDelete(ScheduleData);
		        String output =	schO.removeSchedule(sb);

		        return output;
			
		}
		
		
	    }

		
		

	

