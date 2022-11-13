package com.document.view;

import javax.ejb.EJB;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.document.bean.CurrentUserService;
import com.document.entity.User;
import com.document.entity.UserAdmin;
import com.document.entity.UserAuthor;
import com.document.entity.UserReviewer;
import com.document.interfaces.LogIn;
import com.document.repository.UserRepository;

@Named("logIn")
@RequestScoped
public class LogInBean implements LogIn {
	private String username;
	private String password;

	@Inject
	UserRepository ur;

	@EJB
	CurrentUserService cus;
	
	public String logIn() {
		User user = ur.findWithCriteria(username, password);
		
		cus.setCurrentUser(user);
		
		if(user instanceof UserAdmin) {
			return "admin";
		} else if(user instanceof UserAuthor) {
			return "author";
		} else if(user instanceof UserReviewer) {
			return "reviewer";
		}
		
		return "fail";
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
