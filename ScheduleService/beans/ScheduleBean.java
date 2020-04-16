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
	private String Status;
	private String adminID;
	
	public ScheduleBean() {
		
	}
	
	public ScheduleBean(String sch) {

        JsonObject schO = new JsonParser().parse(sch).getAsJsonObject();

        if (schO.get("ScheduleID") !=null) {
            this.id = schO.get("ScheduleID").getAsInt();
        }
        this.ScheduleDate = schO.get("ScheduleDate").getAsString();
        this.ScheduleTime = schO.get("ScheduleTime").getAsString();
        this.ScheduleType = schO.get("ScheduleType").getAsString();
        this.Status = schO.get("Status").getAsString();
        this.adminID = schO.get("adminID").getAsString();


    }

public ScheduleBean(int id, String ScheduleDate, String ScheduleTime, String ScheduleType, String status,String adminID,String Status) {
		
		this.id = id;
		this.ScheduleDate = ScheduleDate;
		this.ScheduleTime = ScheduleTime;
		this.ScheduleType = ScheduleType;
		this.Status = Status;
		this.adminID = adminID;
	}
	
public ScheduleBean( String ScheduleDate, String ScheduleTime, String ScheduleType, String status,String adminID,String Status) {
		
		
		this.ScheduleDate = ScheduleDate;
		this.ScheduleTime = ScheduleTime;
		this.ScheduleType = ScheduleType;
		this.Status = Status;
		this.adminID = adminID;
	}


	public ScheduleBean(int int1, String string, String string2, String string3, String query) {
	// TODO Auto-generated constructor stub
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