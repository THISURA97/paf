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

import bean.SpecializationBean;
import service.Specialization;

@Path("/Specialization")
public class SpecializationService {

	Specialization specialization = new Specialization();
	
	@PermitAll
	@GET
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	public List<SpecializationBean> viewsSpecializations() {
		return specialization.viewsSpecializations();
	}
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_HTML)
	public String insertSpecializations(@FormParam ("name") String name) {
		SpecializationBean sb = new SpecializationBean(0,name);
		return specialization.insertSpecialization(sb);
	}
	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_HTML)
	public String updateSpecializations(@FormParam("name") String name, @FormParam ("sid") String sid) {
		int id = Integer.parseInt(sid);
		SpecializationBean sb = new SpecializationBean(id,name);
		return specialization.updateSpecialization(sb);
		
	}
	
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_HTML)
	public String removeSpecializations(@FormParam ("sid") String sid) {
		int id = Integer.parseInt(sid);
		return specialization.removeSpecialization(id);
		
	}
	
	@POST
	@Path("/byID")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public List <SpecializationBean> viewSpecializationsByID(@FormParam ("sid") String sid){
		int id = Integer.parseInt(sid);
		return specialization.getSpecializationList(id);
	}
}
