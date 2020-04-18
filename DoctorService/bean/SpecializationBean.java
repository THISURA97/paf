package bean;

public class SpecializationBean {
	private int id;
	private String name;
	
	
	
	/**
	 * @param id
	 * @param name
	 */
	public SpecializationBean(int id, String name) {
		
		this.id = id;
		this.name = name;
	}

	public SpecializationBean() {
	
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
