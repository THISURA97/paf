package bean;

public class HospitalBean {
	
	private int id;
	private String name;
	

	/**
	 * @param id
	 * @param name
	 */
	public HospitalBean(int id, String name) {
		
		this.id = id;
		this.name = name;
	}

	

	/**
	 * 
	 */
	public HospitalBean() {
		
	}



	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
	
}
