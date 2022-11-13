package com.document.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;

@SuppressWarnings("serial")
@Entity(name = "UserReviewer")
@DiscriminatorValue("reviewer")
public class UserReviewer extends User {

	@Transient
	private String role = "reviewer";
	
	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
}
