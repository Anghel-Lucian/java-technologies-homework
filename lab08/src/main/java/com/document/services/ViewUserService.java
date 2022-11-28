package com.document.services;

import java.util.ArrayList;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import com.document.entity.User;
import com.document.repository.UserRepository;

@Path("/user-view")
public class ViewUserService {
	
	@Inject
	UserRepository ur;
	
	@GET
	public ArrayList<User> getUsers() {
		return ur.findAll();
	}
	
	@GET
	@Path("/{id}")
	public User getUser(@PathParam("id") String id) {
		return  ur.findById(id);
	}
	
}
