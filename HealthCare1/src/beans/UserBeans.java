package beans;

import javax.xml.bind.annotation.XmlRootElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import model.User;

@XmlRootElement
public class UserBeans {
	
	int id;
	private String firstName;
	private String lastName;
	private String dob;
	private String age;
	private String gender;
	private String address;
	private String phone;
	private String email;
	
	
	public UserBeans() {}
	
	public UserBeans(String usr) {
		
		JsonObject userObject = new JsonParser().parse(usr).getAsJsonObject();
		
		if (userObject.get("userID") !=null) {
			
			this.id = userObject.get("userID").getAsInt();
		}
		
		this.firstName = userObject.get("firstName").getAsString();	
		this.lastName = userObject.get("lastName").getAsString();	
		this.dob = userObject.get("dob").getAsString();	
		this.age = userObject.get("age").getAsString();	
		this.gender = userObject.get("gender").getAsString();	
		this.address = userObject.get("address").getAsString();	
		this.phone = userObject.get("phone").getAsString();
		this.email = userObject.get("email").getAsString();	
	}
	
	public UserBeans (int id, String firstName, String lastName, String dob, String age, String gender, String address, String phone, String email) {
		
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.dob = dob;
		this.age = age;
		this.gender = gender;
		this.address = address;
		this.phone = phone;
		this.email = email;
	}
	
	public UserBeans (String firstName, String lastName, String dob, String age, String gender, String address, String phone, String email) {
		
		this.firstName = firstName;
		this.lastName = lastName;
		this.dob = dob;
		this.age = age;
		this.gender = gender;
		this.address = address;
		this.phone = phone;
		this.email = email;
	}
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstname() {
		return firstName;
	}

	public void setFirstname(String firstName) {
		this.firstName = firstName;
	}

	public String getLaststname() {
		return lastName;
	}

	public void setLaststname(String lastName) {
		this.lastName = lastName;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	
	
	
	
	
}
