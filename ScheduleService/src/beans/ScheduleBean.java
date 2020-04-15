package src.beans;

import javax.xml.bind.annotation.XmlRootElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

@XmlRootElement
public class ScheduleBean {

	
	int id;
	private String ScheduleDate;
	private String ScheduleTime;
	private String ScheduleType;
	private String adminID;
	
	
public ScheduleBean(int i, String Schedule, String string, String string2, String query) {
		
		JsonObject sch = new JsonParser().parse(Schedule).getAsJsonObject();
	
		if (sch.get("ScheduleID") != null) 
		{
		this.id = sch.get("ScheduleID").getAsInt();
		}
		
		this.ScheduleDate = sch.get("ScheduleDate").getAsString();
		this.ScheduleTime = sch.get("ScheduleTime").getAsString();
		this.ScheduleType = sch.get("ScheduleType").getAsString();
		this.adminID = sch.get("adminID").getAsString();

}

	

public ScheduleBean(int id, String ScheduleDate, String ScheduleTime, String ScheduleType, String status,String adminID) {
		
		this.id = id;
		this.ScheduleDate = ScheduleDate;
		this.ScheduleTime = ScheduleTime;
		this.ScheduleType = ScheduleType;
		this.adminID = adminID;
	}
	
public ScheduleBean( String ScheduleDate, String ScheduleTime, String ScheduleType, String status,String adminID) {
		
		
		this.ScheduleDate = ScheduleDate;
		this.ScheduleTime = ScheduleTime;
		this.ScheduleType = ScheduleType;
		this.adminID = adminID;
	}


	public ScheduleBean() {

}

	 public ScheduleBean(String scheduleData) {
		// TODO Auto-generated constructor stub
	}



	public String getAdminID() {
	        return adminID;
	    }

	    public void setAdminID(String adminID) {
	        this.adminID = adminID;
	    }

	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getScheduleDate() {
		return ScheduleDate;
	}


	public void setScheduleDate(String scheduleDate) {
		ScheduleDate = scheduleDate;
	}


	public String getScheduleTime() {
		return ScheduleTime;
	}


	public void setScheduleTime(String scheduleTime) {
		ScheduleTime = scheduleTime;
	}


	public String getScheduleType() {
		return ScheduleType;
	}


	public void setScheduleType(String scheduleType) {
		ScheduleType = scheduleType;
	}


	
	
}
