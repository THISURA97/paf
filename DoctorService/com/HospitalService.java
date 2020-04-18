package com;

import java.util.List;

import javax.annotation.security.PermitAll;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import bean.HospitalBean;
import service.Hospital;

@Path("/Hospital")
public class HospitalService {
	Hospital hospital = new Hospital();

	@PermitAll
	@GET
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	public List<HospitalBean> viewsHospitals() {
		return hospital.getHospitalList(0);
	}

	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_HTML)
	public String insertHospital(@FormParam ("name") String name) {
		HospitalBean hb =  new HospitalBean(0,name);
		
		return hospital.insertHospital(hb);
		
	}
	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_HTML)
	public String updateHospital(@FormParam ("name") String name, @FormParam ("hid") String hid) {
		int id = Integer.parseInt(hid);
		HospitalBean hb =  new HospitalBean(id,name);
		return hospital.updateHospital(hb);
	}
	
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_HTML)
	public String deleteHospital(@FormParam ("hid") String hid) {
		int id = Integer.parseInt(hid);
		return hospital.removeHospital(id);
	}
	
	@POST
	@Path("/byID")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public List<HospitalBean> viewHospitalsByID(@FormParam ("hid") String hid){
		int id =  Integer.parseInt(hid);
		return hospital.getHospitalList(id);
	}
	
}
