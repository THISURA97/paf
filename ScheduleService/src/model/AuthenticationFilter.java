package src.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Set;
import javax.ws.rs.ext.Provider;
 
import src.util.DBConnection;

@Provider
public class AuthenticationFilter 
{
	public static boolean isUserAllowed(final String username, final String password, final Set<String> roleType)
    {
        boolean isAllowed = false;
        String role = "" ; 
        String name = "" ;
        String pass = "" ;
        
        try {
    		Connection con = DBConnection.connect();
    		if (con == null) {
    			
    		}
    		
    		String queryUser = "select * from users where username='"+username+"' and password='"+password+"'";
    		String queryDoctor = "select * from doctors where doctorTag='"+username+"' and doctorPass='"+password+"'";
    		String queryAdmin = "select * from admin where adminTag='"+username+"' and adminPass='"+password+"'";
    		
    		PreparedStatement preparedSt_User = con.prepareStatement(queryUser);
    		PreparedStatement preparedSt_Doctor = con.prepareStatement(queryDoctor);
    		PreparedStatement preparedSt_Admin = con.prepareStatement(queryAdmin);
    		
    	   	preparedSt_User.execute();
    	   	preparedSt_Doctor.execute();
    	   	preparedSt_Admin.execute();
    	   	
    		ResultSet rs_User = preparedSt_User.executeQuery(queryUser);
    		ResultSet rs_Doctor = preparedSt_Doctor.executeQuery(queryDoctor);
    		ResultSet rs_Admin = preparedSt_Admin.executeQuery(queryAdmin);
    		 
    		  if(rs_User.next()) {
    			  name = rs_User.getString("username");
    			  pass = rs_User.getString("password");
    			  role = rs_User.getString("role");
    			 
    		  }
    		  if(rs_Doctor.next()) {
    			  name = rs_Doctor.getString("doctorTag");
    			  pass = rs_Doctor.getString("doctorPass");
    			  role = rs_Doctor.getString("role");
    			 
    		  }
    		  if(rs_Admin.next()) {
    			  name = rs_Admin.getString("adminTag");
    			  pass = rs_Admin.getString("adminPass");
    			  role = rs_Admin.getString("role");
    			 
    		  }
    		  con.close();
    		  
    		  
    	} catch (Exception e) {
    				
    				System.err.println(e.getMessage());
    			}
        
        if(username.equals(name) && password.equals(pass))
        {
             
          
            if(roleType.contains(role))
            {
                isAllowed = true;
            }
        }
        return isAllowed;
    }
}
