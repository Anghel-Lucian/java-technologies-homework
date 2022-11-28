package com.document.filters;

import java.io.IOException;
import java.util.HashMap;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.ext.Provider;

import com.document.entity.User;

@Provider
@ApplicationScoped // ApplicationScoped because we want to keep the cache between requests
public class ViewUserCacheFilter implements ContainerRequestFilter, ContainerResponseFilter {

	private HashMap<String, User> cache = new HashMap<String, User>();
	
	@Override
	public void filter(ContainerRequestContext reqContext) throws IOException {
		System.out.print("[CACHE FILTER]: Request method fired");
		UriInfo uriInfo = reqContext.getUriInfo();
		
 		String userId = uriInfo.getPath().split("/")[1];
		
		if(this.cache.keySet().contains(userId)) {
			System.out.print("[CACHE FILTER]: aborting with object stored in cache");
			reqContext.abortWith(Response.status(Status.OK).entity(this.cache.get(userId)).build());
		}

	}
	
	// this method will run even if you return the user from cache, but it's working, because this method runs when
	// the reqContext.abortWith() method executes from above
    @Override
    public void filter(ContainerRequestContext reqContext, ContainerResponseContext resContext) throws IOException {
    	System.out.print("[CACHE FILTER]: Response method fired");
    	UriInfo uriInfo = reqContext.getUriInfo();
    	
    	if(uriInfo.getPath().equals("user-view") || uriInfo.getPath().equals("user-update")) {
    		return;
    	}
    	
    	User user = (User) resContext.getEntity();
        
    	this.storeUserInCache(user.getId(), user);
     }

    private void storeUserInCache(String userId, User user) {
    	System.out.print("[CACHE FILTER]: Put in cache user with id: " + userId);
    	this.cache.put(userId, user);
    }
}