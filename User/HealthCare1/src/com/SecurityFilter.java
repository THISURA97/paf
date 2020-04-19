package com;

import java.lang.reflect.Method;

import java.io.IOException;
import java.util.Arrays;
import java.util.Base64;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.client.authentication.HttpAuthenticationFeature;
import org.glassfish.jersey.jackson.JacksonFeature;


import javax.annotation.security.DenyAll;
import javax.annotation.security.PermitAll;

import model.Authentication;

@Provider
public class SecurityFilter implements ContainerRequestFilter {
	

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
				
				String decodedString = "";
				try {
					byte[] decodedBytes = Base64.getDecoder().decode(authTag);
					decodedString = new String(decodedBytes, "UTF-8");
				} catch (IOException e) {
					e.printStackTrace();
				}
				final StringTokenizer tokenizer = new StringTokenizer(decodedString, ":");

				final String username = tokenizer.nextToken();
				final String password = tokenizer.nextToken();
			
				if(method.isAnnotationPresent(RolesAllowed.class))
	            {
	                RolesAllowed rolesAnnotation = method.getAnnotation(RolesAllowed.class);
	                Set<String> roleType = new HashSet<String>(Arrays.asList(rolesAnnotation.value()));
	                  
	                ClientConfig clientConfig = new ClientConfig();
					HttpAuthenticationFeature feature = HttpAuthenticationFeature.basic(username, password);
					clientConfig.register(feature);

					clientConfig.register(JacksonFeature.class);

					Client client = ClientBuilder.newClient(clientConfig);
					WebTarget webTarget;

					if (roleType.contains("administrator")) {
						webTarget = client.target("http://localhost:8081/AuthService/AuthService").path("users/admin");

					} else if (roleType.contains("doctor")) {
						webTarget = client.target("http://localhost:8081/AuthService/AuthService").path("users/doctor");
					} else {
						webTarget = client.target("http://localhost:8081/AuthService/AuthService")
								.path("users/patient");
					}

					Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);

					Response response = invocationBuilder.get();

					if (response.getStatus() != 200) {
						Response unauthoriazedStatus = Response.status(Response.Status.UNAUTHORIZED).entity("{\"error\" : \"not authorized 3\"}").build();
						requestContext.abortWith(unauthoriazedStatus);

					}
					return;
	               
	              
	            }
			
				
			
			}
		Response unauthoriazedStatus = Response.status(Response.Status.UNAUTHORIZED)
				.entity("{\"error\" : \"not authorized 1\"}").build();
		requestContext.abortWith(unauthoriazedStatus);

	    }

}


