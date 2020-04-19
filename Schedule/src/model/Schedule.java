package model;

import java.sql.*;

public class Schedule
{
	private Connection connect() {
		Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			 //Provide the correct details: DBServer/DBName, username, password
			 con = DriverManager.getConnection("Jdbc:mysql://localhost:3306/test?serverTimezone=UTC", "root", ""); 
		}catch (Exception e) {
			{
				e.printStackTrace();
			}
		}
		
		return con;
	}
	public String insertItem(String date, String time, String type,String doc,String hos)
	 {
	 String output = "";
	 try
	 {
	 Connection con = connect();
	 if (con == null)
	 {return "Error while connecting to the database for inserting."; }
	 // create a prepared statement
	 String query = " insert into schedule(`scheduleID`,`scheduleDate`,`scheduleTime`,`scheduleType`,`scheduleDoc`,`scheduleHos`)"
	 + " values (?, ?, ?, ?,?,?)";
	 PreparedStatement preparedStmt = con.prepareStatement(query);
	 // binding values
	 preparedStmt.setInt(1, 0);
	 preparedStmt.setString(2, date);
	 preparedStmt.setString(3, time);
	 preparedStmt.setString(4, type); 
	 preparedStmt.setString(5, doc); 
	 preparedStmt.setString(6, hos); 
	
	 // execute the statement
	 preparedStmt.execute();
	 con.close();
	 output = "schedule details inserted successfully";
	 }
	 catch (Exception e)
	 {
	 output = "Error while inserting the schedule details.";
	 System.err.println(e.getMessage());
	 }
	 return output;
	 }
	public String readItems()
	 {
	 String output = "";
	 try
	 {
	 Connection con = connect();
	 if (con == null)
	 {return "Error while connecting to the database for reading."; }
	 // Prepare the html table to be displayed
	 output =
			 " <link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css\">\r\n" + 
			 "  <script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js\"></script>\r\n" + 
			 "  <script src=\"https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js\"></script>\r\n" + 
			 "  <script src=\"https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js\"></script>"
			 +" <div class=\"container\">"
			 +"<h2>Schedule Management</h2>"
			 +"<table class=\"table\">"
			 +"<thead class=\"thead-dark\">"
			 + "<tr>"
			 + "<th>Date</th>" 
			 + "<th>Time</th>"
			 + "<th>Type</th>"
			 + "<th>Doctor</th>"
			 + "<th>Hospital</th>"
			 + "</tr>"
			 +"<thead>"
			 +"</div>";
	 String query = "select * from schedule";
	 Statement stmt = con.createStatement();
	 ResultSet rs = stmt.executeQuery(query);
	 // iterate through the rows in the result set
	 while (rs.next())
	 {
	 String scheduleID = Integer.toString(rs.getInt("scheduleID"));
	 String scheduleDate = rs.getString("scheduleDate");
	 String scheduleTime = rs.getString("scheduleTime");
	 String scheduleType = rs.getString("scheduleType");
	 String scheduleDoc = rs.getString("scheduleDoc");
	 String scheduleHos = rs.getString("scheduleHos");
	 // Add into the html table
	 output += "<tr><td>" + scheduleDate + "</td>";
	 output += "<td>" + scheduleTime + "</td>";
	 output += "<td>" + scheduleType + "</td>";
	 output += "<td>" + scheduleDoc + "</td>";
	 output += "<td>" + scheduleHos + "</td>";
	 
	 }
	 con.close();
	 // Complete the html table
	 output += "</table>";
	 }
	 catch (Exception e)
	 {
	 output = "Error while reading the schedule details.";
	 System.err.println(e.getMessage());
	 }
	 return output;
	 } 
	
	
	public String readItemsType()
	 {
	 String output = "";
	 try
	 {
	 Connection con = connect();
	 if (con == null)
	 {return "Error while connecting to the database for reading."; }
	 // Prepare the html table to be displayed
	 output =
			 " <link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css\">\r\n" + 
			 "  <script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js\"></script>\r\n" + 
			 "  <script src=\"https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js\"></script>\r\n" + 
			 "  <script src=\"https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js\"></script>"
			 +" <div class=\"container\">"
			 +"<h2>Consulting Schedule Management</h2>"
			 +"<table class=\"table\">"
			 +"<thead class=\"thead-dark\">"
			 + "<tr>"
			 + "<th>Date</th>" 
			 + "<th>Time</th>"
			 + "<th>Doctor</th>"
			 + "<th>Hospital</th>"
			 + "</tr>"
			 +"<thead>"
			 +"</div>";
	 String query = "select * from schedule WHERE scheduleType IN( 'consulting') ";
	 Statement stmt = con.createStatement();
	 ResultSet rs = stmt.executeQuery(query);
	 // iterate through the rows in the result set
	 while (rs.next())
	 {
	 String scheduleID = Integer.toString(rs.getInt("scheduleID"));
	 String scheduleDate = rs.getString("scheduleDate");
	 String scheduleTime = rs.getString("scheduleTime");
	 String scheduleDoc = rs.getString("scheduleDoc");
	 String scheduleHos = rs.getString("scheduleHos");
	 // Add into the html table
	 output += "<tr><td>" + scheduleDate + "</td>";
	 output += "<td>" + scheduleTime + "</td>";
	 output += "<td>" + scheduleDoc + "</td>";
	 output += "<td>" + scheduleHos + "</td>";
	 
	 }
	 con.close();
	 // Complete the html table
	 output += "</table>";
	 }
	 catch (Exception e)
	 {
	 output = "Error while reading the schedule details.";
	 System.err.println(e.getMessage());
	 }
	 return output;
	 } 
	
	
	public String updateItem(String ID, String date, String time, String type,String doc,String hos)
	 {
	 String output = "";
	 try
	 {
	 Connection con = connect();
	 if (con == null)
	 {return "Error while connecting to the database for updating."; }
	 // create a prepared statement
	 String query = "UPDATE items SET scheduleDate=?,scheduleTime=?,scheduleType=?,,scheduleDoc=?,scheduleHos=? WHERE scheduleID=?";
	 PreparedStatement preparedStmt = con.prepareStatement(query);
	 // binding values
	 preparedStmt.setString(1, date);
	 preparedStmt.setString(2, time);
	 preparedStmt.setString(3, type);
	 preparedStmt.setString(4, doc);
	 preparedStmt.setString(5, hos);
	 preparedStmt.setInt(6, Integer.parseInt(ID));
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
	
	public String deleteItem(String scheduleID)
	 {
	 String output = "";
	 try
	 {
	 Connection con = connect();
	 if (con == null)
	 {return "Error while connecting to the database for deleting."; }
	 // create a prepared statement
	 String query = "delete from schedule where scheduleID=?";
	 PreparedStatement preparedStmt = con.prepareStatement(query);
	 // binding values
	 preparedStmt.setInt(1, Integer.parseInt(scheduleID));
	 // execute the statement
	 preparedStmt.execute();
	 con.close();
	 output = "Deleted successfully";
	 }
	 catch (Exception e)
	 {
	 output = "Error while deleting the schedule details.";
	 System.err.println(e.getMessage());
	 }
	 return output;
	 } 
	
}

