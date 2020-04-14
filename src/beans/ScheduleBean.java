package beans;

import javax.xml.bind.annotation.XmlRootElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

@XmlRootElement
public class ScheduleBean {

	
	int id;
	private String ScheduleDate;
	private String ScheduleTime;
	private String ScheduleType;
	
	
public ScheduleBean(String Schedule) {
		
		JsonObject sch = new JsonParser().parse(Schedule).getAsJsonObject();
	
		if (sch.get("ScheduleID") != null) 
		{
		this.id = sch.get("ScheduleID").getAsInt();
		}
		
		this.ScheduleDate = sch.get("ScheduleDate").getAsString();
		this.ScheduleTime = sch.get("ScheduleTime").getAsString();
		this.ScheduleType = sch.get("ScheduleType").getAsString();
	}

	

public ScheduleBean(int id, String ScheduleDate, String ScheduleTime, String ScheduleType, String status) {
		
		this.id = id;
		this.ScheduleDate = ScheduleDate;
		this.ScheduleTime = ScheduleTime;
		this.ScheduleType = ScheduleType;
	}
	
public ScheduleBean( String ScheduleDate, String ScheduleTime, String ScheduleType, String status) {
		
		
		this.ScheduleDate = ScheduleDate;
		this.ScheduleTime = ScheduleTime;
		this.ScheduleType = ScheduleType;
	}


	public ScheduleBean() {

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
