package beans;

import javax.xml.bind.annotation.XmlRootElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

@XmlRootElement
public class ScheduleBean {

	
	private int ScheduleID;
	private String ScheduleDate;
	private String ScheduleTime;
	private String ScheduleType;
	private String Status;
	private String adminID;
	
	private JsonObject ScheduleObject;
	
	public ScheduleBean() {
		
	}
	
	public ScheduleBean(String sch) {

        JsonObject schO = new JsonParser().parse(sch).getAsJsonObject();

        if (schO.get("ScheduleID") !=null) {
            this.setScheduleID(schO.get("ScheduleID").getAsInt());
        }
        this.ScheduleDate = schO.get("ScheduleDate").getAsString();
        this.ScheduleTime = schO.get("ScheduleTime").getAsString();
        this.ScheduleType = schO.get("ScheduleType").getAsString();
        this.Status = schO.get("Status").getAsString();
        this.adminID = schO.get("adminID").getAsString();


    }

public void convertStringToJSONInsert(String ScheduleData) {
		
	ScheduleObject = new JsonParser().parse(ScheduleData).getAsJsonObject();
		setScheduleID(ScheduleObject.get("ScheduleID").getAsInt());
		setScheduleDate(ScheduleObject.get("ScheduleDate").getAsString());
		setScheduleTime(ScheduleObject.get("ScheduleTime").getAsString());
		setScheduleType(ScheduleObject.get("ScheduleType").getAsString());
		setStatus(ScheduleObject.get("Status").getAsString());
		setAdminID(ScheduleObject.get("AdminID").getAsString());
	}
	
public void convertStringToJSONUpdate(String ScheduleData) {
	
	ScheduleObject = new JsonParser().parse(ScheduleData).getAsJsonObject();
		setScheduleID(ScheduleObject.get("ScheduleID").getAsInt());
		setScheduleDate(ScheduleObject.get("ScheduleDate").getAsString());
		setScheduleTime(ScheduleObject.get("ScheduleTime").getAsString());
		setScheduleType(ScheduleObject.get("ScheduleType").getAsString());
		setStatus(ScheduleObject.get("Status").getAsString());
		setAdminID(ScheduleObject.get("AdminID").getAsString());
	}
	
public void convertStringToJSONDelete(String ScheduleData) {
	
	ScheduleObject  = new JsonParser().parse(ScheduleData).getAsJsonObject(); 		
	setScheduleID(ScheduleObject.get("ScheduleID").getAsInt());
}
	public String getStatus() {
			return Status;
		}

		public void setStatus(String Status) {
			this.Status = Status;
		}

	public String getAdminID() {
	        return adminID;
	    }

	    public void setAdminID(String adminID) {
	        this.adminID = adminID;
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

	public int getScheduleID() {
		return ScheduleID;
	}

	public void setScheduleID(int scheduleID) {
		ScheduleID = scheduleID;
	}


	
	
}