package model;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;



import beans.ScheduleBean;
import util.DBConnection;

public class Schedule {
	
	public String output;
	private String query;
	private PreparedStatement preparedSt;
	private Connection con;
	
	//Insert a Schedule
	public String insertSchedule(ScheduleBean schB) {
		
	
     output = "";

    try {

    	 con = DBConnection.connect();

        if (con == null) {

            return "Error - while inserting the Schedule details.";

        }
		 query =" INSERT INTO Schedule"
						+ "(ScheduleID, ScheduleDate, ScheduleTime, ScheduleType) "
						+ "values (?,?,?,?)";
			
		 preparedSt = con.prepareStatement(query);
			
			
			//blinding values
			preparedSt.setInt(1, 0);
			preparedSt.setString(2, schB.getScheduleDate());
			preparedSt.setString(3, schB.getScheduleTime());
			preparedSt.setString(4, schB.getScheduleType());
			
			//Execute the prepared statements
			
			preparedSt.execute();
			  con.close();

	            output = "Schedule details inserted successfully.";

	        } catch (Exception e) {

	            output = "Error -  while inserting the Schedule details.";
	            System.err.println(e.getMessage());

	        }

	        return output;
	}
	
	
	public String viewSchedule() {
		output = "";
		
	
	
	ScheduleBean sbr = new ScheduleBean();
				
			try 
			{
				Connection con = DBConnection.connect();
				if (con == null) {
					
					return "Error - While trying to view from database";
					
				}
				
				output="<table>"
						+ "<th>ScheduleID</th>"
						+ "<th>ScheduleDate</th>"
						+ "<th>ScheduleTime</th>"
						+ "<th>ScheduleType</th>"
						+ "</table>";
						
						
				
				query = "select * from Schedule";
				
				Statement st = con.createStatement();
				ResultSet rs = st.executeQuery(query);

				while (rs.next()) 
				{
					sbr.setScheduleID(rs.getInt("ScheduleID"));
					sbr.setScheduleDate(rs.getString("ScheduleDate"));
					sbr.setScheduleTime(rs.getString("ScheduleTime"));
					sbr.setScheduleType(rs.getString("ScheduleType"));
					
					output += "<tr><td>" + sbr.getScheduleID() + "</td>";
					output += "<td>" + sbr.getScheduleDate() + "</td>";
					output += "<td>" + sbr.getScheduleTime() + "</td>";
					output += "<td>" + sbr.getScheduleType() + "</td></tr>";
					
				}
				con.close();

			}
			catch (Exception e) {
				System.out.println("Error - While trying to view schedule details");
				System.err.println(e.getMessage());
			}
			
			return output;
		}
		
		//Update Schedule Details
		public String updateSchedule(ScheduleBean schB) {
			 output = "";
			try {
				 con = DBConnection.connect();

		            if (con == null) {

		                return "Error -  while updating the Schedule details.";

		            }
			
				String query = "update Schedule set"
								+"s_date=?,"
								+ "s_time=?,"
								+ "s_type=?,"
								+"where s_id = ? ";
						

				 preparedSt = con.prepareStatement(query);

				
				// binding values
				preparedSt.setString(1, schB.getScheduleDate());
				preparedSt.setString(2, schB.getScheduleTime());
				preparedSt.setString(3, schB.getScheduleType());			
				preparedSt.setInt(4, schB.getScheduleID());
				
				

				// Prepared Statement Execution
				preparedSt.execute();
				con.close();
				output = "Doctor details updated successfully.";

	        } catch (Exception e) {

	            output = "Error -  while updating the Schedule details.";
	            System.err.println(e.getMessage());

	        }

	        return output;

		}

		
		//remove schedule
		public String removeSchedule(ScheduleBean schB) {
			  output = "";

		        try {

		        	 con = DBConnection.connect();
				if (con == null) {
					return "Error - occurred while deleting the schedule details.";
				}
				
				// Prepared Statement
				 query = "delete from Schedule where ScheduleID=?";
				
				 preparedSt = con.prepareStatement(query);

				
				// Binding values
				preparedSt.setInt(1, schB.getScheduleID());

				
				// execute the statement
				preparedSt.executeUpdate();
				con.close();
				 output = "Schedule details deleted successfully.";

	        } catch (Exception e) {

	            output = "Error -  while deleting the schedule details.";
	            System.err.println(e.getMessage());

	        }

	        return output;
		}

		
		
}
