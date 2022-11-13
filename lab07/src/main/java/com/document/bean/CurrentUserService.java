package com.document.bean;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.document.entity.User;
import com.document.entity.UserAdmin;
import com.document.entity.UserAuthor;
import com.document.entity.UserReviewer;

@Stateless
@LocalBean
public class CurrentUserService {
	
	private User currentUser;

	public User getCurrentUser() {
		return this.currentUser;
	}

	public void setCurrentUser(User currentUser) {
		if(currentUser instanceof UserAdmin) {
			System.out.print("ADMIN LOGGED IN");
		} else if(currentUser instanceof UserAuthor) {
			System.out.print("AUTHOR LOGGED IN");
		} else if(currentUser instanceof UserReviewer) {
			System.out.print("REVIEWER LOGGED IN");
		}
		
		this.currentUser = currentUser;
	}
}
