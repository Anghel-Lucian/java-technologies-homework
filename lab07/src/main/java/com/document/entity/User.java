package com.document.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@SuppressWarnings("serial")
@Entity
@Table(name="user")
@DiscriminatorColumn(name="role", discriminatorType = DiscriminatorType.STRING)
public class User implements Serializable {
	
	@Id
	private String id;
	
	@NotNull
	@Size(min=1,max=40)
	@Column(name="username")
	private String username;
	
	@NotNull
	@Size(min=1,max=40)
	@Column(name="password")
	private String password;
	
	@Column(name="publications")
	private String publications;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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
