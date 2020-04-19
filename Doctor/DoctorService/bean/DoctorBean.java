package bean;


public class DoctorBean {
	private int id;
	private String firstName;
	private String middleName;
	private String lastname;
	private String contact;
	private String status;
	/**
	 * @param id
	 * @param firstName
	 * @param middleName
	 * @param lastname
	 * @param contact
	 * @param status
	 */
	public DoctorBean(int id, String firstName, String middleName, String lastname, String contact, String status) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastname = lastname;
		this.contact = contact;
		this.status = status;
	}
	
	public DoctorBean() {}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getMiddleName() {
		return middleName;
	}
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
	
	
}
