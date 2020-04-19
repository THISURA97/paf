package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.SpecializationBean;
import util.DBConnection;

public class Specialization {

	// View Specialization list by ID . if passed 0 get all the Specializations
		public List<SpecializationBean> getSpecializationList(int id) {
			List<SpecializationBean> specializationList = new ArrayList<>();
			Connection connection = DBConnection.getConnection();
			ResultSet resultSet = null;
			String query;
			if (id == 0) {
				query = "SELECT * FROM speciality";
			} else {
				query = "SELECT * FROM speciality WHERE id = " + id;
			}
			try {
				PreparedStatement preparedStatement = connection.prepareStatement(query);
				resultSet = preparedStatement.executeQuery();

				while (resultSet.next()) {
					int sid = resultSet.getInt("id");
					String sname = resultSet.getString("name");
					
					SpecializationBean sb = new SpecializationBean(sid, sname);
					specializationList.add(sb);
				}
				connection.close();

			} catch (Exception e) {
				System.err.println(e.getMessage());
			}
			return specializationList;

		}

		// get all Specializations
		public List<SpecializationBean> viewsSpecializations() {
			return getSpecializationList(0);
		}

		// get Only one Specialization by ID
		public SpecializationBean getSpecializationID(int id) {
			List<SpecializationBean> list = getSpecializationList(id);
			if (!list.isEmpty()) {
				return list.get(0);
			}
			return null;

		}
		// Insert a Specialization
		public String  insertSpecialization(SpecializationBean sb) {
			String status = "Insert Didn't Occure Yet";
			Connection connection = DBConnection.getConnection();
			String query = "INSERT INTO `speciality`" 
					+"(`name`)"
					+"VALUES(?)";
			try {
				PreparedStatement preparedStatement = connection.prepareStatement(query);
				
				preparedStatement.setString(1,sb.getName());
				
				preparedStatement.execute();
				connection.close();
				status = "Insert Success";
			}
			catch(Exception e) {
				status = "Inserting Interrupted by Error";
				System.err.println(e.getMessage());
			}
			return status;
			
		}
		
		//Update a Specialization
		public String updateSpecialization(SpecializationBean sb) {
			String status = "Update Didn't Occure Yet";
			Connection connection = DBConnection.getConnection();
			String query = "UPDATE speciality SET" 
					+"`name` = ? "
					+"WHERE `id` = ?";
			try {
				PreparedStatement preparedStatement = connection.prepareStatement(query);
				
				preparedStatement.setString(1,sb.getName());
				preparedStatement.setInt(2,sb.getId());
				preparedStatement.execute();
				connection.close();
				status = "Update Success";
			}
			catch(Exception e) {
				status = "Update Interrupted by Error";
				System.err.println(e.getMessage());
			}
			return status;
		}
		
		//Remove a Specialization by id
		public String removeSpecialization(int id) {
			String status = "Delete Query Didn't Occure Yet";
			Connection connection = DBConnection.getConnection();
			String query = "DELETE FROM speciality WHERE id = ?";
			try {
				PreparedStatement preparedStatement = connection.prepareStatement(query);
				preparedStatement.setInt(1,id);
				preparedStatement.execute();
				connection.close();
				status = "Remove Success";
				
			}catch(Exception e) {
				status = "Remove Interrupted by Error";
				System.err.println(e.getMessage());
			}
			return status;
		}

}
