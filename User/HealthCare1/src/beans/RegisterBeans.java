package beans;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class RegisterBeans {
	
	int registerID;
	private String userName;
	private String email;
	private String password;
	private String phone;
	
	public RegisterBeans() {}
	
	public RegisterBeans(int registerID, String userName, String email, String password, String phone) {
		super();
		this.registerID = registerID;
		this.userName = userName;
		this.email = email;
		this.phone = phone;
	}
	
	public RegisterBeans(int registerId, String userName, String email, String phone) {
		super();
		this.registerID = registerId;
		this.userName = userName;
		this.email = email;
		this.phone = phone;
	}

	public int getRegisterID() {
		return registerID;
	}

	public void setRegisterID(int registerId) {
		this.registerID = registerId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

}
