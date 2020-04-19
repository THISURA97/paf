package com;

import model.Appointment;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;



@Path("appointment")

public class AppointmentService {

	
	Appointment appointmentObj = new Appointment();

	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readItems() {
		return appointmentObj.readAppointment();
	}

	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertAppointment(@FormParam("userID") int userID, @FormParam("doctorID") int doctorID,
			@FormParam("appointmentDate") String appointmentDate, @FormParam("appointmentTime") String appointmentTime,
			@FormParam("tokenNo") int tokenNo, @FormParam("payType") String payType,
			@FormParam("amount") double amount) {
		String output = appointmentObj.insertAppointment(userID, doctorID, appointmentDate, appointmentTime, tokenNo,
				payType, amount);
		return output;
	}

	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateAppointment(@FormParam ("appointmentID") String appointmentID,@FormParam("userID") String userID, @FormParam("doctorID") String doctorID,
			@FormParam("appointmentDate") String appointmentDate, @FormParam("appointmentTime") String appointmentTime,
			@FormParam("tokenNo") String tokenNo) {

		String output = appointmentObj.updateAppointment(appointmentID, userID, doctorID, appointmentDate,
				appointmentTime, tokenNo);
		return output;
	}

	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteAppointment(@FormParam ("appointmentID") String appointmentID) {
		
		
		String output = appointmentObj.deleteAppointment(appointmentID);
		return output;

	}
}
