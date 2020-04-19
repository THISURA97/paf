package model;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;

import util.DBconnection;

public class Payment {
	
	private Connection connect() {
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");

			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/healthcare", "root", "");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}
	
	public String insertPayment(String paymentID, String appointmentID, String cardNo, String expireDate, String cvv, String cardholderName) {
		
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for inserting.";
			}

			String query = " insert into payment ('paymentID', 'appointmentID','cardNo','expireDate', 'cvv', 'cardholderName' )"
				+ " values (?, ?, ?, ?, ?, ?)";


			PreparedStatement preparedStmt = con.prepareStatement(query);
			preparedStmt.setString(1, paymentID);
			preparedStmt.setString(2, appointmentID);
			preparedStmt.setString(3, cardNo);
			preparedStmt.setString(4, expireDate);
			preparedStmt.setString(5, cvv);
			preparedStmt.setString(6, cardholderName);
			

			preparedStmt.execute();
			con.close();

			output = "Inserted successfully";
			}
		catch (Exception e) {
			output = "Error while inserting the data.";
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
			if (con == null){
				return "Error while connecting to the database for reading."; 
			
		}
	
		output = "<table border=\"1\"><tr><th>paymentID</th><th>appointmentID</th><th>cardNo</th><th>expireDate</th><th>cvv</th><th>cardholderName</th></tr>";
		String query = "select * from payment";
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(query);

		

		while (rs.next()) {

			String paymentID = rs.getString("paymentID");
			String appointmentID = rs.getString("appointmentID");
			String cardNo = rs.getString("cardNo");
			String expireDate = rs.getString("expireDate");
			String cvv =  rs.getString(rs.getInt("cvv"));
			String cardholderName = rs.getString("cardholderName");
			

			System.out.println(paymentID + paymentID);

			output += "<tr><td>" + paymentID + "</td>";
			output += "<td>" + appointmentID + "</td>";
			output += "<td>" + cardNo + "</td>";
			output += "<td>" + expireDate + "</td>";
			output += "<td>" + cvv + "</td>";
			output += "<td>" + cardholderName + "</td>";
			
			
			output += "</tr>";
		}
			output += "</table>";
		} 
		catch (Exception e){
			output = "Error while reading the records.";
			System.err.println(e.getMessage());
		}
		return output;
	}
	
		
	
	public String updatePayment(String paymentID, String appointmentID, String cardNo, String expireDate, String cvv, String cardholderName) {

		String output = "";

		try {

			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for updating.";
			}

			String query = "UPDATE payment SET  appointmentID= ?, cardNo= ?, expireDate= ?, cvv= ?, cardholderName= ? WHERE paymentID= ?";

			PreparedStatement preparedStmt = con.prepareStatement(query);

			
			preparedStmt.setString(1, appointmentID);
			preparedStmt.setString(2, cardNo);
			preparedStmt.setString(3, expireDate);
			preparedStmt.setString(4, cvv);
			preparedStmt.setString(5, cardholderName);
			

			preparedStmt.execute();

			con.close();
			output = "Updated successfully";

		} 
		catch (Exception e) {
			output = "Error while updating data";
			System.err.println(e.getMessage());
		}
		return output;
	}
	
	public String deletePayment(String paymentID)
	{
		String output = "";
		try
		{
			Connection con = connect();
			if (con == null)
		{
		return "Error while connecting to the database for deleting."; }
	
		String query = "delete from payment where paymentID=?";
		PreparedStatement preparedStmt = con.prepareStatement(query);
	
		//preparedStmt.setInt(1, Integer.parseInt(paymentID));

		preparedStmt.execute();
		con.close();
		output = "Deleted successfully";
		}
		catch (Exception e)
		{
			output = "Error while deleting the data.";
			System.err.println(e.getMessage()); 
		}
		return output;
	} 

}
