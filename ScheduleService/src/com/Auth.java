package src.com;

import java.lang.reflect.Method;
import java.io.IOException;
import java.util.Arrays;
import java.util.Base64;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.Provider;
import javax.annotation.security.DenyAll;
import javax.annotation.security.PermitAll;
import src.model.AuthenticationFilter;


@Provider
public class Auth implements ContainerRequestFilter {
		
	@Context
	private ResourceInfo resourceInfo;

	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException
	{
		
		System.out.println("SecurityFilter : Executed");
		List <String> auth = requestContext.getHeaders().get("Authorization");
		
		System.err.println("GFA Debug SecurityInterceptor ............ ");
	    System.err.println(requestContext.getUriInfo().getRequestUri());
		
		Method method = resourceInfo.getResourceMethod();
		
		
	    System.out.println("GFA DEbug method.getName() " + method.getName());
	    System.out.println("GFA DEbug method.isAnnotationPresent(PermitAll.class) = " + method.isAnnotationPresent(PermitAll.class));
		 
			// Access denied for all
	            if(method.isAnnotationPresent(DenyAll.class))
	            {
	            	Response unauthoriazedStatus = Response.status(Response.Status.UNAUTHORIZED).entity("Access denied for this resource").build();
	            	requestContext.abortWith(unauthoriazedStatus);
	                return;
	            }
	            
	         // Access allowed for all
	            if (method.isAnnotationPresent(PermitAll.class)) 
	            {
	                System.out.println("GFA debug permitAll ... ");
	                return;
	            }  
		if( auth.size() > 0 && auth != null)
		{
				String authTag = auth.get(0);
				authTag = authTag.replaceFirst("Basic", "");
				
				byte[] decodedStr = Base64.getDecoder().decode(authTag);
				
				String NameAndPass = new String(decodedStr, "UTF-8");
				StringTokenizer tokenizer = new StringTokenizer(NameAndPass, ":");
			
				final String username = tokenizer.nextToken();
				final String password = tokenizer.nextToken();
			
				if(method.isAnnotationPresent(RolesAllowed.class))
	            {
	                RolesAllowed rolesAnnotation = method.getAnnotation(RolesAllowed.class);
	                Set<String> roleType = new HashSet<String>(Arrays.asList(rolesAnnotation.value()));
	                  
	                
	                if( ! AuthenticationFilter.isUserAllowed(username, password, roleType))
	                {
	                	Response response = Response.status(Status.UNAUTHORIZED).entity("User Can't Access this Resource").build();	                	
	                	requestContext.abortWith(response);
	                   
	                }
	                return;
	            }
			
				
			
			}
	    }
	

	}




