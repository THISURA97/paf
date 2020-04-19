package beans;

import javax.xml.bind.annotation.XmlRootElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import model.User;

@XmlRootElement(name = "UserBeans")
public class UserBeans {
	
	int userID;
	private String firstName;
	private String lastName;
	private String dob;
	private String age;
	private String gender;
	private String address;
	private String phone;
	private String email;
	private String username;
	private String password;
	
	
	public UserBeans() {}
	
	public UserBeans(String usr) {
		
		JsonObject userObject = new JsonParser().parse(usr).getAsJsonObject();
		
		if (userObject.get("userID") !=null) {
			
			this.userID = userObject.get("userID").getAsInt();
		}
		
		this.firstName = userObject.get("firstName").getAsString();	
		this.lastName = userObject.get("lastName").getAsString();	
		this.dob = userObject.get("dob").getAsString();	
		this.age = userObject.get("age").getAsString();	
		this.gender = userObject.get("gender").getAsString();	
		this.address = userObject.get("address").getAsString();	
		this.phone = userObject.get("phone").getAsString();
		this.email = userObject.get("email").getAsString();	
		this.username = userObject.get("username").getAsString();	
		this.password = userObject.get("password").getAsString();	
	}
	
	public UserBeans (int userID, String firstName, String lastName, String dob, String age, String gender, String address, String phone, String email, String username, String password) {
		
		this.userID = userID;
		this.firstName = firstName;
		this.lastName = lastName;
		this.dob = dob;
		this.age = age;
		this.gender = gender;
		this.address = address;
		this.phone = phone;
		this.email = email;
		this.email = username;
		this.email = password;
	}
	
	public UserBeans (String firstName, String lastName, String dob, String age, String gender, String address, String phone, String email, String username, String password) {
		
		this.firstName = firstName;
		this.lastName = lastName;
		this.dob = dob;
		this.age = age;
		this.gender = gender;
		this.address = address;
		this.phone = phone;
		this.email = email;
		this.email = username;
		this.email = password;
	}
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
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
