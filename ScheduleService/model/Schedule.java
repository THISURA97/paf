package model;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;

import beans.ScheduleBean;
import util.DBConnection;

public class Schedule {
	
	//Insert a Schedule
	public Response insertSchedule(ScheduleBean schB ,UriInfo uri)
	{

		Response response;
		String output = "{\"status\":\"success\"}";
		 int tag=Tag();
		try {
			Connection con = DBConnection.connect();
			if (con == null) {
				output = "{\"status\":\"Connection faild\"}";
				return Response.status(Status.INTERNAL_SERVER_ERROR)
						.entity(output).build();
			}
			String query =" INSERT INTO `Schedule`"
						+ "(`ScheduleID`, `ScheduleDate`, `ScheduleTime`, `ScheduleType`) "
						+ "values (?,?,?,?)";
			
			PreparedStatement preparedSt = con.prepareStatement(query);
			
			
			//blinding values
			preparedSt.setInt(1, 0);
			preparedSt.setString(2, schB.getScheduleDate());
			preparedSt.setString(3, schB.getScheduleTime());
			preparedSt.setString(4, schB.getScheduleType());
			
			//Execute the prepared statements
			
			preparedSt.execute();
			output = "{\"status\":\"success\"}";
			response = Response.created(uri.getAbsolutePathBuilder().path(""+tag).build())
			.entity(output).build();
			con.close();
			
		} catch (Exception e) {
			output = "{\"status\":"+e.getMessage()+"}";
			response=Response.status(Status.INTERNAL_SERVER_ERROR)
			.entity(output).build();
			System.err.println(e.getMessage());
		}
		
		
		return response;
	}
	
	public List<ScheduleBean> viewSchedule() {
		
		return	viewSchedule(0);

	}
	
	public ScheduleBean viewScheduleById(int id) {
	List<ScheduleBean> list =viewSchedule(id);
		if(!list.isEmpty()) {
			return	list.get(0);
		}
		return null;
	}
	
	//View the Schedule
		public List<ScheduleBean> viewSchedule(int id) {
				List <ScheduleBean> ScheduleList = new ArrayList<>();
				
			try 
			{
				Connection con = DBConnection.connect();
				if (con == null) {
					
					System.out.println("Error - While trying to view from database");
					return ScheduleList;
				}
				String query;
				if(id == 0) {
					query = "select * from Schedule";
				}else {
					query = "select * from Schedule where ScheduleID="+id;
				}
				
				Statement st = con.createStatement();
				ResultSet rs = st.executeQuery(query);

				while (rs.next()) 
				{
					ScheduleBean schB = new ScheduleBean
							(
									rs.getInt("ScheduleID"),
									rs.getString("ScheduleDate"),
									rs.getString("ScheduleTime"),
									rs.getString("ScheduleType"),
									query
									);
												
										
					ScheduleList.add(schB);

				}
				con.close();

			}
			catch (Exception e) {
				System.out.println("Error - While trying to view schedule details");
				System.err.println(e.getMessage());
			}
			
			return ScheduleList;
		}
		
		//Update Schedule Details
		public Response updateSchedule(ScheduleBean schB, UriInfo uri) {
			String output = "";
			Response response;
			try {
				Connection con = DBConnection.connect();
				if (con == null) {
					output = "{\"status\":\"Connection faild\"}";
					return Response.status(Status.INTERNAL_SERVER_ERROR)
							.entity(output).build();
				}
			
				String query = "update `Schedule` set"
								+"`s_date`=?,`s_time`=?,`s_type`=?,"
								+"where s_id = ? ";
						

				PreparedStatement preparedSt = con.prepareStatement(query);

				
				// binding values
				preparedSt.setString(1, schB.getScheduleDate());
				preparedSt.setString(2, schB.getScheduleTime());
				preparedSt.setString(3, schB.getScheduleType());			
				preparedSt.setInt(4, schB.getId());
				
				

				// Prepared Statement Execution
				preparedSt.execute();
				con.close();
				output = "{\"status\":\"success\"}";
				response = Response.accepted(uri.getAbsolutePathBuilder().path(""+schB.getId()).build())
				.entity(output).build();
			} catch (Exception e) {
				output = "{\"status\":"+e.getMessage()+"}";
				response=Response.status(Status.INTERNAL_SERVER_ERROR)
				.entity(output).build();
				System.err.println(e.getMessage());
			}
			return response;
		}

		
		//remove schedule
		public Response removeSchedule(String ScheduleID) {
			String output = "";
			
			Response response;
			try {
				Connection con = DBConnection.connect();
				if (con == null) {
					output = "{\"status\":\"Connection faild\"}";
					return Response.status(Status.INTERNAL_SERVER_ERROR)
							.entity(output).build();
				}
				
				// Prepared Statement
				String query = "delete from Schedule where ScheduleID=?";
				
				PreparedStatement preparedSt = con.prepareStatement(query);

				
				// Binding values
				preparedSt.setInt(1, Integer.parseInt(ScheduleID));

				
				// execute the statement
				preparedSt.execute();
				con.close();
				output = "{\"status\":\"success\"}";
				response = Response.status(Status.ACCEPTED)
				.entity(output).build();
			} catch (Exception e) {
				output = "{\"status\":"+e.getMessage()+"}";
				response=Response.status(Status.INTERNAL_SERVER_ERROR)
				.entity(output).build();
				System.err.println(e.getMessage());
			}
			return response;
		}

		public List<ScheduleBean> getShedulesByDate(String Sch_Date){
			List<ScheduleBean> list = new ArrayList<>();
		
			for(ScheduleBean schB :viewSchedule()){
				if(schB.getScheduleDate()== Sch_Date) {
					list.add(schB);
				}		
			}
			
			return list;
		}
		
		public List<ScheduleBean> getShedulesByTime(String Sch_Time){
			List<ScheduleBean> list = new ArrayList<>();
		
			for(ScheduleBean schB : viewSchedule()){
				if(schB.getScheduleTime()==Sch_Time) {
					list.add(schB);
				}		
			}
			
			return list;
		}
		
		public List<ScheduleBean> getShedulesByType(String Sch_Type){
			List<ScheduleBean> list = new ArrayList<>();
		
			for(ScheduleBean schB : viewSchedule()){
				if(schB.getScheduleType()==Sch_Type) {
					list.add(schB);
				}		
			}
			
			return list;
		}
		
		public int Tag(){
			int id =0 ;
			for(ScheduleBean sch : viewSchedule()){
				if(id<sch.getId()) {
				 id =sch.getId();
				}		
			}
			return id+1;
		}
		

		
		
}
