package com.document.entity;
	
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;
	
@SuppressWarnings("serial")
@Entity(name = "UserAuthor")
@DiscriminatorValue("author")
public class UserAuthor extends User {
	
	@Transient
	private String role = "author";
	
	@Column(name="publications")
	private String publications;
	
	public String getPublications() {
		return publications;
	}
	
	public void setPublications(String publications) {
		this.publications = publications;
	}
	
	public String getRole() {
		return role;
	}
	
	public void setRole(String role) {
		this.role = role;
	}
}	
