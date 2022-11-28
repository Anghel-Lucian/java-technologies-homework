package com.document.services;

import javax.inject.Inject;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;

import com.document.repository.UserRepository;

@Path("/user-update")
public class UpdateUserService {

	@Inject
	UserRepository ur;
	
	@PUT
	@Path("/username/{id}")
	public String updateUsername(@PathParam("id") String id, @QueryParam("username") String username) {
		ur.updateUsername(id, username);
		
		return "Updated username: " + username;
	}
	
	@PUT
	@Path("/password/{id}")
	public String updatePassword(@PathParam("id") String id, @QueryParam("password") String password) {
		ur.updatePassword(id, password);
		
		return "Updated password: " + password;
	}
	
}
