package com.document.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;

@SuppressWarnings("serial")
@Entity(name = "UserAdmin")
@DiscriminatorValue("admin")
public class UserAdmin extends User {
	
	@Transient
	private String role = "admin";

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
}
