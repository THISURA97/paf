package model;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;



import beans.ScheduleBean;
import util.DBConnection;

public class Schedule {
	
	//Insert a Schedule
	
	

	

	public String insertSchedule(String ScheduleId,String ScheduleDate,String ScheduleTime,String ScheduleType) {

		String output = "";
		
		try 
		{
			Connection con = DBConnection.connect();
			if (con == null) {
				return "Error while connecting to the database for inserting.";
			}
			
				
			String query =" INSERT INTO `Schedule`"
						+ "(`s_ID`, `s_date`, `s_time`, `s_type`) "
						+ "VALUES (?,?,?,?)";
			
			PreparedStatement preparedStmt = con.prepareStatement(query);
			
			

			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, ScheduleDate);
			preparedStmt.setString(3, ScheduleTime);
			preparedStmt.setString(4, ScheduleType);
			
			
			
			//Prepared Statement Execution
			preparedStmt.execute();
			con.close();
			output = "Inserted successfully";
			System.out.println(output);
			
		} catch (Exception e) {
			
			output = "Error while inserting the schedule.";
			System.err.println(e.getMessage());
		}
		return output;
	}
	
	
	//View the Schedule
		public List<ScheduleBean> viewSchedule() {
				List <ScheduleBean> ScheduleList = new ArrayList<>();
				
			try 
			{
				Connection con = DBConnection.connect();
				if (con == null) {
					
					System.out.println("Error While reading from database");
					return ScheduleList;
				}

				String query = "select * from Schedule";
				Statement st = con.createStatement();
				ResultSet rs = st.executeQuery(query);

				while (rs.next()) 
				{
					ScheduleBean schedule = new ScheduleBean
							(
									rs.getString("s_ID"),
									rs.getString("s_date"),
									rs.getString("s_time"),
									rs.getString("s_type")
												
													
							);
					ScheduleList.add(schedule);

				}
				con.close();

			}
			catch (Exception e) {
				System.out.println("Error While Reading");
				System.err.println(e.getMessage());
			}
			
			return ScheduleList;
		}
		
		//Update Schedule Details
		public String updateSchedule(ScheduleBean sch) {
			String output = "";
			
			try 
			{
				Connection con = DBConnection.connect();
				
				if (con == null) {
					return "Error while connecting to the database for updating.";
				}
				
				
			
				String query = "UPDATE `schedule` SET"
								+"`s_date`=?,`s_time`=?,`s_type`=?,"
								+"WHERE s_id = ? ";
						

				PreparedStatement preparedStmt = con.prepareStatement(query);

				
				// binding values
				preparedStmt.setString(1, sch.getScheduleDate());
				preparedStmt.setString(2, sch.getScheduleTime());
				preparedStmt.setString(3, sch.getScheduleType());			
				
				
				preparedStmt.setString(4, sch.getScheduleID());
				
				

				// Prepared Statement Execution
				preparedStmt.execute();
				con.close();
				output = "Updated successfully";
				
			} 
			catch (Exception e) {
				output = "Error while updating the Schedule.";
				System.err.println(e.getMessage());
			}
			
			return output;
		}

		public String removeSchedule(String ScheduleID) {
			String output = "";
			
			try 
			{
				Connection con = DBConnection.connect();
			
				if (con == null) {
					return "Error while connecting to the database for deleting.";
				}
				
				
				// Prepared Statement
				String query = "delete from schedule where s_ID=?";
				
				PreparedStatement preparedSt = con.prepareStatement(query);

				
				// Binding values
				preparedSt.setInt(1, Integer.parseInt(ScheduleID));

				
				// execute the statement
				preparedSt.execute();
				con.close();
				output = "Deleted successfully";
				
			} 
			catch (Exception e) {
				output = "Error while deleting the Schedule.";
				System.err.println(e.getMessage());
			}
			
			return output;
		}


		

		
		
}
