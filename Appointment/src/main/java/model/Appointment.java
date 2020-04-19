package model;

import util.DBConnection;

import java.sql.*;

public class Appointment {
	

	
	public String insertAppointment(int userID, int doctorID, String appointmentDate, String appointmentTime, int tokenNo, String payType, double amount ){
		
		String output = "";

        try {
        	Connection con = null;
            con = DBConnection.connect();

            if (con == null) {

                return "Error while connecting to the database while inserting.";

            }

		String query ="INSERT INTO appointment(userID, doctorID, appointmentDate, appointmentTime, tokenNo,payType, amount) "+ " VALUES (?, ?, ?, ?, ?, ?, ?);";
		
		PreparedStatement preparedStmt = con.prepareStatement(query);
		 // binding values
	
		 preparedStmt.setInt(1, userID);
		 preparedStmt.setInt(2, doctorID);
		 preparedStmt.setString(3, appointmentDate); 
		 preparedStmt.setString(4, appointmentTime); 
		 preparedStmt.setInt(5, tokenNo); 
		 preparedStmt.setString(6, payType); 
		 preparedStmt.setDouble(7, amount);
		 // execute the statement
		 preparedStmt.execute();
		 
		
		 
		 con.close();
          output = "Inserted successfully to Appointment";
		}
		
		catch (Exception e) {

        output = "Error Occurred while inserting the Appointment.";
        System.err.println(e.getMessage());

    }

    return output;
		
	}
	
	public String readAppointment() {
	
		String output = "";
		
	    try {
        	Connection con = null;
            con = DBConnection.connect();

            if (con == null) {

                return "Error while connecting to the database while reading.";

            }
			
			output = 
					 " <link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css\">\r\n" + 
							 "  <script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js\"></script>\r\n" + 
							 "  <script src=\"https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js\"></script>\r\n" + 
							 "  <script src=\"https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js\"></script>"
							 +" <div class=\"container\">"
							 +"<h2>Appointment Management</h2>"
							 +"<table class=\"table\">"
							 +"<thead class=\"thead-dark\">"
							 + "<tr>"
							 + "<th>AppointmentID</th>"
			                 + "<th>User ID</th>"
			                 + "<th>Doctor ID</th>"
			                 + "<th>Appointment Date</th>"
			                 + "<th>Appointment Time</th>"
			                 + "<th>Token No.</th>"
							 + "</tr>"
							 +"<thead>"
							 +"</div>";
			
			String query = "select * from appointment";
			 Statement stmt = con.createStatement();
			 ResultSet rs = stmt.executeQuery(query);
			 // iterate through the rows in the result set
			 while (rs.next())
			 {
			 String appointmentID = Integer.toString(rs.getInt("appointmentID"));
			 int userID = rs.getInt("userID");
			 int doctorID = rs.getInt("doctorID");
			 String appointmentDate = rs.getString("appointmentDate");
			 String appointmentTime = rs.getString("appointmentTime");
			 int tokenNo = rs.getInt("tokenNo");
			 // Add into the html table
			 output += "<tr><td>" + appointmentID + "</td>";
             output += "<td>" + userID + "</td>";
             output += "<td>" + doctorID+ "</td>";
             output += "<td>" + appointmentDate + "</td>";
             output += "<td>" + appointmentTime + "</td>";
             output += "<td>" + tokenNo + "</td></tr>";
			 
			 }
			 
			 con.close();
			 // Complete the html table
			 output += "</table>";
							 
		}
		catch (Exception e)
		 {
		 output = "Error while reading the Appointment details.";
		 System.err.println(e.getMessage());
		 }
		 return output;
		 } 
	
	
	public String updateAppointment(String appointmentID, String userID, String doctorID, String appointmentDate, String appointmentTime, String tokenNo) {
		String output = "";
		
		try {
        	Connection con = null;
            con = DBConnection.connect();

            if (con == null) {

                return "Error while connecting to the database while updating.";

            }
            
         String   query = " UPDATE appointment SET "+
                 " userID = ?, "+
                 " doctorID = ?, "+
                 " appointmentDate = ?, "+
                 " appointmentTime = ?, "+
                 " tokenNo = ? "+
                 " WHERE appointmentID = ?";

         PreparedStatement preparedStmt = con.prepareStatement(query);
    	 // binding values
    	 preparedStmt.setInt(1, Integer.parseInt(userID));
    	 preparedStmt.setInt(2, Integer.parseInt(doctorID));
    	 preparedStmt.setString(3, appointmentDate);
    	 preparedStmt.setString(4, appointmentTime);
    	 preparedStmt.setInt(5, Integer.parseInt(tokenNo));
    	 preparedStmt.setInt(6, Integer.parseInt(appointmentID));
    	 // execute the statement
    	 preparedStmt.execute();
    	 con.close();
    	 output = "Updated successfully";
    	 }
    	 catch (Exception e)
    	 {
    	 output = "Error while updating the schedule details.";
    	 System.err.println(e.getMessage());
    	 }
    	 return output;
		
	}
	
	public String deleteAppointment(String appointmentID)
	 {
	 String output = "";
	 try {
     	Connection con = DBConnection.connect();

         if (con == null) {

             return "Error while connecting to the database while deleting.";

         }
	 // create a prepared statement
	 String query = "delete from appointment where appointmentID = ?";
	  
	 PreparedStatement preparedStmt = con.prepareStatement(query);
	 // binding values
	 preparedStmt.setInt(1, Integer.parseInt(appointmentID));
	
	 // execute the statement
	 preparedStmt.execute();
	 con.close();
	 output = "Deleted successfully";
	 }
	 catch (Exception e)
	 {
	 output = "Error while deleting the appointment details.";
	 e.printStackTrace();
	 }
	 return output;
	 } 
	
	
	
	


}
