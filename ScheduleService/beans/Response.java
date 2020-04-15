package beans;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class Response implements Serializable{

    
	private static final long serialVersionUID = 1L;
	private boolean status;
    private String message;
    private Object data;

    public Response() {}

    public Response(boolean status, String message) {

        this.status = status;
        this.message = message;

    }

    public Response(Object data) {

        this.status=true;
        this.data=data;

    }

    public static Response Error(Exception e) {
        return new Response(false, e.getMessage());
    }

    public static Response OK(String message) {
        return new Response(true, message);
    }

    public static Response OK(Object data) {
        return new Response(data);
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

}