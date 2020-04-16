package com;

import java.net.URISyntaxException;
import java.util.List;
import javax.annotation.security.RolesAllowed;
import javax.websocket.server.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
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
		public Response readSchedule(@QueryParam("ScheduleDate") String Sch_Date, @QueryParam("ScheduleTime") String Sch_Time,
				@QueryParam("ScheduleType") String Sch_Type) {
			List<ScheduleBean> list;
			Response response;

			if (Sch_Date != null) {
				list = schO.getShedulesByDate(Sch_Date);
				response = Response.ok(schO.getShedulesByDate(Sch_Date)).build();

			} else if (Sch_Time != null) {
				list = schO.getShedulesByTime(Sch_Time);
				response = Response.ok(schO.getShedulesByTime(Sch_Time)).build();

			} else if (Sch_Type != null) {
				list = schO.getShedulesByType(Sch_Type);

				response = Response.ok(schO.getShedulesByType(Sch_Type)).build();

			} else {
				list = schO.viewSchedule();
				response = Response.ok(schO.viewSchedule()).build();
			}

			if (!list.isEmpty()) {
				return response;
			}
			return Response.noContent().build();
		}
		
		@RolesAllowed({"administrator","doctor"})
		@GET
		@Path("/{ScheduleID}")
		@Produces(MediaType.APPLICATION_JSON)
		public Response viewScheduleById(@PathParam("PatientID") int id) {
			ScheduleBean schB = schO.viewScheduleById(id);
			if (schB != null) {
				return Response.ok().entity(schO.viewScheduleById(id)).build();
			}
			return Response.noContent().build();
		}
		
		
		@RolesAllowed({"administrator","doctor"}) 
		//Insert a Schedule
		@POST
		@Path("/")
		@Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.APPLICATION_JSON)
		public Response insertSchedule(ScheduleBean schB, @Context UriInfo uri) throws URISyntaxException {
			Response response = schO.insertSchedule(schB, uri);
			return response;
			
		} 
			
		
		@RolesAllowed({"administrator","doctor"})
		@PUT
		@Path("/")
		@Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.APPLICATION_JSON)
		public Response updateSchedule(@PathParam("ScheduleID") int ScheduleID, ScheduleBean schB, @Context UriInfo uri)
				throws URISyntaxException {
			schB.setId(ScheduleID);
			return schO.updateSchedule(schB, uri);
		}
		
		
		@RolesAllowed({"administrator","doctor"}) 
		
		//Remove a Schedule
		@DELETE
		@Path("/")
		@Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.TEXT_PLAIN)
		public Response removeSchedule(@PathParam("ScheduleID") String ScheduleID) {
			return schO.removeSchedule(ScheduleID);
		}
		}

	

