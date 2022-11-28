package com.document.services;

import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import com.document.entity.User;
import com.document.repository.UserRepository;

@Path("/user-delete")
public class DeleteUserService {
	
	@Inject
	UserRepository ur;
	
	@DELETE
	@Path("/{id}")
	public Response deleteUser(@PathParam("id") String id) {
		User user = ur.findById(id);
		
		if(user != null) {
			ur.deleteUser(id);			
			
			return Response.ok().entity("User deleted!").build();
		} else {
			return Response.noContent().build();
		}

	}
	
}
