package bean;

public class DoctorHospitalBean {
	
	private int hid;
	private int did;
	private String status;
	
	
	
	/**
	 * @param hid
	 * @param did
	 * @param status
	 */
	public DoctorHospitalBean(int hid, int did, String status) {
		super();
		this.hid = hid;
		this.did = did;
		this.status = status;
	}



	public DoctorHospitalBean() {
		
	}



	public int getHid() {
		return hid;
	}



	public void setHid(int hid) {
		this.hid = hid;
	}



	public int getDid() {
		return did;
	}



	public void setDid(int did) {
		this.did = did;
	}



	public String getStatus() {
		return status;
	}



	public void setStatus(String status) {
		this.status = status;
	}
	
	
}
