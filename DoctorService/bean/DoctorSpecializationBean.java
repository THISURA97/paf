package bean;

public class DoctorSpecializationBean {

	private int did;
	private int spid;
	
	
	/**
	 * @param did
	 * @param spid
	 */
	public DoctorSpecializationBean(int did, int spid) {
		super();
		this.did = did;
		this.spid = spid;
	}

	public DoctorSpecializationBean() {
		
	}
	
	public int getDid() {
		return did;
	}

	public void setDid(int did) {
		this.did = did;
	}

	public int getSpid() {
		return spid;
	}

	public void setSpid(int spid) {
		this.spid = spid;
	}
	

}
