package com;

import java.util.List;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import javax.annotation.security.PermitAll;

import bean.DoctorBean;
import bean.DoctorHospitalBean;
import bean.DoctorSpecializationBean;

import service.Doctor;
import service.Specialization;

@Path("/Doctor")
public class DoctorService {
	Doctor doctor = new Doctor();
	Specialization specialization = new Specialization();

	@PermitAll
	@GET
	@Path("/html")
	@Produces(MediaType.TEXT_HTML)
	public String readDoctors() {
		return doctor.readDoctors();
	}

	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_HTML)
	public String insertDoctors(@FormParam("id") String did, @FormParam("firstName") String firstName,
			@FormParam("middleName") String middleName, @FormParam("lastname") String lastname,
			@FormParam("contact") String contact, @FormParam("status") String status) {
		int id = Integer.parseInt(did);
		DoctorBean dm = new DoctorBean(id, firstName, middleName, lastname, contact, status);
		return doctor.insertDoctor(dm);
	}

	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_HTML)
	public String updateDoctors(@FormParam("id") String did, @FormParam("firstName") String firstName,
			@FormParam("middleName") String middleName, @FormParam("lastname") String lastname,
			@FormParam("contact") String contact, @FormParam("status") String status) {
		int id = Integer.parseInt(did);
		DoctorBean dm = new DoctorBean(id, firstName, middleName, lastname, contact, status);
		return doctor.updateDoctor(dm);
	}

	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_HTML)
	public String deleteDoctors(@FormParam("id") String did) {
		int id = Integer.parseInt(did);
		return doctor.removeDoctor(id);
	}

	@GET
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	public List<DoctorBean> viewDoctors() {
		return doctor.getDoctorList(0);
	}

	@POST
	@Path("/byID")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public List<DoctorBean> viewDoctorsByID(@FormParam("id") String did) {
		int id = Integer.parseInt(did);
		return doctor.getDoctorList(id);

	}

	@PermitAll
	@GET
	@Path("/Spec")
	@Produces(MediaType.APPLICATION_JSON)
	public List<DoctorSpecializationBean> viewsDoctorSpecializationList() {
		return doctor.getDoctorSpecializationList(0, 0);
	}

	@POST
	@Path("/Spec")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public String insertDoctorSpecialization(@FormParam("docID") String docID, @FormParam("specID") String specID) {
		int did = Integer.parseInt(docID);
		int sid = Integer.parseInt(specID);
		DoctorSpecializationBean dsb = new DoctorSpecializationBean(did, sid);
		return doctor.insertDoctorSpecialization(dsb);
	}

	@DELETE
	@Path("/Spec")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public String removeDoctorSpecialization(@FormParam ("docID") String docID, @FormParam ("specID") String specID ) {
		int did =  Integer.parseInt(docID);
		int sid = Integer.parseInt(specID);
		DoctorSpecializationBean dsb =  new DoctorSpecializationBean(did,sid);
		return doctor.removeDoctorSpecialization(dsb);
		
	}

	@PermitAll
	@GET
	@Path("/Hosp")
	@Produces(MediaType.APPLICATION_JSON)
	public List<DoctorHospitalBean> viewsHospitalDoctorList() {
		return doctor.getHospitalDoctorList(0, 0);
	}

	@POST
	@Path("/Hosp")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public String insertDoctorHospitals(@FormParam("docID") String docID, @FormParam("hospID") String hospID,
			@FormParam("status") String status) {
		int did = Integer.parseInt(docID);
		int hid = Integer.parseInt(hospID);
		DoctorHospitalBean dhb = new DoctorHospitalBean(hid, did, status);
		return doctor.insertHospitalDoctor(dhb);
	}

	@PUT
	@Path("/Hosp")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public String updateDoctorHospitals( @FormParam ("hospID") String hospID, @FormParam ("status") String status,@FormParam ("docID") String docID) {
		int did =  Integer.parseInt(docID);
		int hid = Integer.parseInt(hospID);
		DoctorHospitalBean dhb = new DoctorHospitalBean(hid,did,status);
		return doctor.updateHospitalDoctor(dhb);
	}

	@DELETE
	@Path("/Hosp")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public String removeDoctorHospital(@FormParam("docID") String docID, @FormParam("hospID") String hospID) {
		int did =  Integer.parseInt(docID);
		int hid = Integer.parseInt(hospID);
		
		return doctor.removeHospitalDoctor(did, hid);
	}
	
}
