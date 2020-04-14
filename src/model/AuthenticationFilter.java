package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Set;
import javax.ws.rs.ext.Provider;
 
import util.DBConnection;

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
    		
    		String query = "select * from users where username='"+username+"' and password='"+password+"'";
    		PreparedStatement preparedSt = con.prepareStatement(query);
    		
    	
    	   	preparedSt.execute();
    		ResultSet rs = preparedSt.executeQuery(query);
    		 
    		  if(rs.next()) {
    			  name = rs.getString("username");
    			  pass = rs.getString("password");
    			  role = rs.getString("role");
    			 
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
