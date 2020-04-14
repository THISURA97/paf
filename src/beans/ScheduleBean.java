package beans;

import javax.xml.bind.annotation.XmlRootElement;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

@XmlRootElement
public class ScheduleBean {

	
	private String ScheduleID;
	private String ScheduleDate;
	private String ScheduleTime;
	private String ScheduleType;
	
	
	public ScheduleBean() {
		
	}


	public String getScheduleID() {
		return ScheduleID;
	}


	public void setScheduleID(String scheduleID) {
		ScheduleID = scheduleID;
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


	public ScheduleBean(String Schedule) {
		
		JsonObject scheduleObject = new JsonParser().parse(Schedule).getAsJsonObject();
	
		if (scheduleObject.get("ScheduleID") != null) 
		{
		this.ScheduleID = scheduleObject.get("ScheduleID").getAsString();
		}
		
		this.ScheduleDate = scheduleObject.get("ScheduleDate").getAsString();
		this.ScheduleTime = scheduleObject.get("ScheduleTime").getAsString();
		this.ScheduleType = scheduleObject.get("ScheduleType").getAsString();
	}

	public ScheduleBean(String scheduleID, String scheduleDate, String scheduleTime, String scheduleType) {
		super();
		this.ScheduleID = scheduleID;
		this.ScheduleDate = scheduleDate;
		this.ScheduleTime = scheduleTime;
		this.ScheduleType = scheduleType;
	}
	
}
