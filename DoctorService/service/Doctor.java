package service;

import bean.DoctorBean;
import bean.DoctorHospitalBean;
import bean.DoctorSpecializationBean;
import util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class Doctor {
	// View doctors list by ID . if passed 0 get all the doctors
	public List<DoctorBean> getDoctorList(int id) {
		List<DoctorBean> doctorsList = new ArrayList<>();
		Connection connection = DBConnection.getConnection();
		ResultSet resultSet = null;

		String query;
		if (id == 0) {
			query = "SELECT * FROM doctor";
		} else {
			query = "SELECT * FROM doctor WHERE id = " + id;
		}
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				int did = resultSet.getInt("id");
				String fname = resultSet.getString("firstname");
				String mname = resultSet.getString("middlename");
				String lname = resultSet.getString("lastname");
				String contact = resultSet.getString("contact");
				String status = resultSet.getString("status");
				DoctorBean DoctorBean = new DoctorBean(did, fname, mname, lname, contact, status);
				doctorsList.add(DoctorBean);
			}
			connection.close();
		} catch (Exception e) {

			e.printStackTrace();
		}

		return doctorsList;
	}

	// get all doctors
	public List<DoctorBean> viewDoctors() {
		return getDoctorList(0);
	}

	// get Only one value by ID
	public DoctorBean getDoctorsListByID(int id) {
		List<DoctorBean> list = getDoctorList(id);
		if (!list.isEmpty()) {
			return list.get(0);
		}
		return null;

	}

	// Insert a doctor

	public String insertDoctor(DoctorBean dm) {
		String status = "Insert Didn't Occure Yet";

		Connection connection = DBConnection.getConnection();
		String query = "INSERT INTO `doctor`" + "(`firstname`,`middlename`,`lastname`,`contact`,`status`)"
				+ "values (?,?,?,?,?)";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(query);

			preparedStatement.setString(1, dm.getFirstName());
			preparedStatement.setString(2, dm.getMiddleName());
			preparedStatement.setString(3, dm.getLastname());
			preparedStatement.setString(4, dm.getContact());
			preparedStatement.setString(5, dm.getStatus());

			preparedStatement.execute();
			connection.close();
			status = "Insert Success";
		} catch (Exception e) {
			status = "Inserting Interrupted by Error";
			System.err.println(e.getMessage());
		}
		return status;
	}

	// Update a doctor
	public String updateDoctor(DoctorBean dm) {
		String status = "Data Update Didn't Occure Yet";
		Connection connection = DBConnection.getConnection();
		String query = "UPDATE `doctor` SET"
				+ " `firstname` = ?, `middlename` = ?, `lastname` = ? , `contact` = ?, `status` = ?" + "WHERE id = ?";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(query);

			preparedStatement.setString(1, dm.getFirstName());
			preparedStatement.setString(2, dm.getMiddleName());
			preparedStatement.setString(3, dm.getLastname());
			preparedStatement.setString(4, dm.getContact());
			preparedStatement.setString(5, dm.getStatus());

			preparedStatement.setInt(6, dm.getId());
			preparedStatement.execute();
			connection.close();
			status = " Update Success";
		} catch (Exception e) {
			status = "Update Interrupted by Error";
			System.err.println(e.getMessage());
		}
		return status;
	}

	// remove a doctor by id
	public String removeDoctor(int id) {
		String status = "Delete Query Didn't Occure Yet";
		Connection connection = DBConnection.getConnection();
		String query = "DELETE FROM doctor WHERE id = ?";
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

	// get Hospital_Doctors
	// View Hospital_Doctors list by IDs . if passed 0 ,0 to get all the
	// Hospital-doctors
	public List<DoctorHospitalBean> getHospitalDoctorList(int did, int hid) {
		List<DoctorHospitalBean> hospitalDoctorList = new ArrayList<>();
		Connection connection = DBConnection.getConnection();
		ResultSet resultSet = null;
		String query;
		if ((did == 0) && (hid == 0)) {
			query = "SELECT * FROM doctor_hosptial";
		}
		// get a list of doctors who're at a specific hospital
		else if (did == 0) {
			query = "SELECT * FROM doctor_hosptial WHERE hid = " + hid;
		}
		// get a list of hospitals which have a specific doctor
		else if (hid == 0) {
			query = "SELECT * FROM doctor_hosptial WHERE did = " + did;
		} else {
			query = "SELECT * FROM doctor_hosptial WHERE did = " + did + " AND hid " + hid;
		}
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				int docid = resultSet.getInt("did");
				int hosid = resultSet.getInt("hid");
				String stat = resultSet.getString("status");

				DoctorHospitalBean hdb = new DoctorHospitalBean(docid, hosid, stat);
				hospitalDoctorList.add(hdb);
			}
			connection.close();

		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return hospitalDoctorList;

	}

	// get all Hospital_Doctors
	public List<DoctorHospitalBean> viewsHospitalDoctorList() {
		return getHospitalDoctorList(0, 0);
	}

	// get Only one specific Hospital_Doctor by DoctorID and Hospital_ID
	public DoctorHospitalBean getHospitalDoctorByID(int did, int hid) {
		List<DoctorHospitalBean> list = getHospitalDoctorList(did, hid);
		if (!list.isEmpty()) {
			return list.get(0);
		}
		return null;
	}

	// Insert a Hospital_Doctor Record
	public String insertHospitalDoctor(DoctorHospitalBean dhb) {
		String status = "Insert Didn't Occure Yet";
		Connection connection = DBConnection.getConnection();
		String query = "INSERT INTO `doctor_hosptial`" + "(`did`,`hid`,`status`)" + "VALUES(?,?,?)";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(query);

			preparedStatement.setInt(1, dhb.getDid());
			preparedStatement.setInt(2, dhb.getHid());
			preparedStatement.setString(3, dhb.getStatus());

			preparedStatement.execute();
			connection.close();
			status = "Insert Success";
		} catch (Exception e) {
			status = "Inserting Interrupted by Error";
			System.err.println(e.getMessage());
		}
		return status;

	}

	// Update a Hospital_Doctor Record
	public String updateHospitalDoctor(DoctorHospitalBean dhb) {
		String status = "Update Didn't Occure Yet";
		Connection connection = DBConnection.getConnection();
		String query = "UPDATE `doctor_hosptial` SET " + " `status` = ? " + "WHERE `hid` = ? AND `did` = ?";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(query);

			preparedStatement.setString(1, dhb.getStatus());
			preparedStatement.setInt(2, dhb.getHid());
			preparedStatement.setInt(3, dhb.getDid());

			preparedStatement.execute();
			connection.close();
			status = "Update Success";
		} catch (Exception e) {
			status = "Update Interrupted by Error";
			System.err.println(e.getMessage());
		}

		return status;

	}

	// Remove a Hospital_Doctor Record
	public String removeHospitalDoctor(int did, int hid) {
		String status = "Delete Query Didn't Occure Yet";
		Connection connection = DBConnection.getConnection();
		String query;

		// delete doctor who's at a specific hospitals
		if (did == 0) {
			query = "DELETE FROM `doctor_hosptial` WHERE hid = " + hid;
		}
		// delete hospital which have a specific doctor
		else if (hid == 0) {
			query = "DELETE FROM `doctor_hosptial` WHERE did = " + did;
		} else {
			query = "DELETE FROM `doctor_hosptial` WHERE did = " + did + " AND hid " + hid;
		}
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(query);

			preparedStatement.execute();
			connection.close();
			status = "Remove Success";

		} catch (Exception e) {
			status = "Remove Interrupted by Error";
			System.err.println(e.getMessage());
		}
		return status;
	}

	// get DoctorSpecialization
	// View Doctor_Specialization list by IDs . if passed 0 ,0 to get all the
	// Doctor-Specialization
	public List<DoctorSpecializationBean> getDoctorSpecializationList(int did, int sid) {
		List<DoctorSpecializationBean> doctorSpecialization = new ArrayList<>();
		Connection connection = DBConnection.getConnection();
		ResultSet resultSet = null;
		String query;
		if ((did == 0) && (sid == 0)) {
			query = "SELECT * FROM doctor_speciality";
		}
		// get a list of doctors who're have a specific specialization
		else if (did == 0) {
			query = "SELECT * FROM doctor_speciality WHERE spid = " + sid;
		}
		// get a list of Specializations of a specific doctor
		else if (sid == 0) {
			query = "SELECT * FROM doctor_speciality WHERE did = " + did;
		} else {
			query = "SELECT * FROM doctor_speciality WHERE did = " + did + " AND spid " + sid;
		}
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				int docid = resultSet.getInt("did");
				int spid = resultSet.getInt("spid");

				DoctorSpecializationBean dsb = new DoctorSpecializationBean(docid, spid);
				doctorSpecialization.add(dsb);
			}
			connection.close();

		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return doctorSpecialization;

	}

	// get all Doctor_Specialization
	public List<DoctorSpecializationBean> viewsDoctorSpecializationList() {
		return getDoctorSpecializationList(0, 0);
	}

	// get Only one specific Doctor_Specialization by DoctorID and Specialization_ID
	public DoctorHospitalBean getDoctorSpecializationByID(int did, int hid) {
		List<DoctorHospitalBean> list = getHospitalDoctorList(did, hid);
		if (!list.isEmpty()) {
			return list.get(0);
		}
		return null;
	}

	// Insert a Doctor_Specialization
	public String insertDoctorSpecialization(DoctorSpecializationBean dsb) {
		String status = "Insert Didn't Occure Yet";
		Connection connection = DBConnection.getConnection();
		String query = "INSERT INTO `doctor_speciality`" + "(`did`,`spid`)" + "VALUES(?,?)";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(query);

			preparedStatement.setInt(1, dsb.getDid());
			preparedStatement.setInt(2, dsb.getSpid());

			preparedStatement.execute();
			connection.close();
			status = "Insert Success";
		} catch (Exception e) {
			status = "Inserting Interrupted by Error";
			System.err.println(e.getMessage());
		}
		return status;
	}

	// Remove a Doctor_Specialization Record by doctor ID and Specialization ID
	public String removeDoctorSpecialization(DoctorSpecializationBean dsb) {
		String status = "Delete Query Didn't Occure Yet";
		Connection connection = DBConnection.getConnection();
		String query = "DELETE FROM `doctor_speciality` WHERE did = ? AND spid = ? ";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, dsb.getDid());
			preparedStatement.setInt(2,dsb.getSpid());
			preparedStatement.execute();
			connection.close();
			status = "Remove Success";

		} catch (Exception e) {
			status = "Remove Interrupted by Error";
			System.err.println(e.getMessage());
		}
		return status;
	}

	// Doctor Table
	public String readDoctors() {
		String out = "";
		Connection connection = DBConnection.getConnection();
		out = "<table border=\"1\"><tr><th>Doctor ID</th><th>First Name</th><th>Middle Name</th><th>Last Name</th><th>Contact</th><th>Status</th><th>Update</th><th>Remove</th></tr>";

		String query = "SELECT * FROM doctor";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				String did = Integer.toString(resultSet.getInt("id"));
				String fname = resultSet.getString("firstname");
				String mname = resultSet.getString("middlename");
				String lname = resultSet.getString("lastname");
				String contact = resultSet.getString("contact");
				String status = resultSet.getString("status");

				out += "<tr>";

				out += " <td>" + did + "	</td>";
				out += " <td>" + fname + "	</td>";
				out += " <td>" + mname + "	</td>";
				out += " <td>" + lname + "	</td>";
				out += " <td>" + contact + "	</td>";
				out += " <td>" + status + "	</td>";

				out += "<td><input name=\"btnUpdate\" type=\"button\"   value=\"Update\" class=\"btn btn-secondary\"></td>"
						+ "<td><form method=\"post\" action=\"Doctor.jsp\">"
						+ "<input name=\"btnRemove\" type=\"submit\" value=\"Remove\" class=\"btn btn-danger\">"
						+ "<input name=\"Doctor_ID\" type=\"hidden\" value=\"" + did + "\">" + "</form></td>";

				out += "</tr>";
				;
			}
			connection.close();

			out += "</table>";
		} catch (Exception e) {
			out = "Reading Interrupted by Error! ";
			System.err.println(e.getMessage());
		}

		return out;
	}
}
