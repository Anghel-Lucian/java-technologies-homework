package com.document.services;

import java.util.UUID;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.document.entity.UserAuthor;
import com.document.entity.UserReviewer;
import com.document.repository.UserRepository;

@Path("/user-add")
public class AddUserService {
	
	@Inject
	UserRepository ur;
	
	@Path("/author")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public UserAuthor createUserAuthor(UserAuthor user) {
		UUID uuid = UUID.randomUUID();
		
		ur.create(user.getUsername(), user.getPassword(), "author", uuid.toString());
		return user;
	}

	@Path("/reviewer")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public UserReviewer createUserReviewer(UserReviewer user) {
		UUID uuid = UUID.randomUUID();
		
		ur.create(user.getUsername(), user.getPassword(), "reviewer", uuid.toString());
		return user;
	}
}
