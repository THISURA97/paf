package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.HospitalBean;
import util.DBConnection;

public class Hospital {
	// View hospital list by ID . if passed 0 get all the hospitals
	public List<HospitalBean> getHospitalList(int id) {
		List<HospitalBean> hospitalList = new ArrayList<>();
		Connection connection = DBConnection.getConnection();
		ResultSet resultSet = null;
		String query;
		if (id == 0) {
			query = "SELECT * FROM hosptial";
		} else {
			query = "SELECT * FROM hosptial WHERE id = " + id;
		}
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				int hid = resultSet.getInt("id");
				String hname = resultSet.getString("name");

				HospitalBean hb = new HospitalBean(hid, hname);
				hospitalList.add(hb);
			}
			connection.close();

		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return hospitalList;

	}

	// get all hospitals
	public List<HospitalBean> viewsHospitals() {
		return getHospitalList(0);
	}

	// get Only one value by ID
	public HospitalBean getHospitalByID(int id) {
		List<HospitalBean> list = getHospitalList(id);
		if (!list.isEmpty()) {
			return list.get(0);
		}
		return null;

	}

	// Insert a hospital
	public String insertHospital(HospitalBean hb) {
		String status = "Insert Didn't Occure Yet";
		Connection connection = DBConnection.getConnection();
		String query = "INSERT INTO `hosptial`" + "(`name`)" + "VALUES(?)";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(query);

			preparedStatement.setString(1, hb.getName());

			preparedStatement.execute();
			connection.close();
			status = "Insert Success";
		} catch (Exception e) {
			status = "Inserting Interrupted by Error";
			System.err.println(e.getMessage());
		}
		return status;

	}

	// Update a hospital
	public String updateHospital(HospitalBean hb) {
		String status = "Update Didn't Occure Yet";
		Connection connection = DBConnection.getConnection();
		String query = "UPDATE `hosptial` SET " + "`name` = ? " + "WHERE `id` = ?";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(query);

			preparedStatement.setString(1, hb.getName());
			preparedStatement.setInt(2, hb.getId());
			preparedStatement.execute();
			connection.close();
			status = "Update Success";
		} catch (Exception e) {
			status = "Update Interrupted by Error";
			System.err.println(e.getMessage());
		}
		return status;
	}

	// Remove a hospital by id
	public String removeHospital(int id) {
		String status = "Delete Query Didn't Occure Yet";
		Connection connection = DBConnection.getConnection();
		String query = "DELETE FROM hosptial WHERE id = ?";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, id);
			preparedStatement.execute();
			connection.close();
			status = "Remove Success";

		} catch (Exception e) {
			status = "Remove Interrupted by Error";
			System.err.println(e.getMessage());
		}
		return status;
	}
	


}
