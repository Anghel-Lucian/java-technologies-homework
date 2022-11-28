package com.document.view;

import java.util.UUID;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.document.repository.SettingsRepository;
import com.document.repository.UserRepository;

@Named("signIn")
@RequestScoped
public class SignInBean {
	private String username;
	private String password;
	private String role;
	private boolean registrationAvailable;
	
	@Inject
	UserRepository ur;
	
	@Inject
	SettingsRepository sr;
	
	@Inject
	UUID uuid;
	
	@PostConstruct
	public void init() {
		this.setRegistrationAvailable(sr.registrationIsAvailable());
	}
	
	public String signIn() {
		if (registrationAvailable) {
			System.out.print("Registration start.");
			ur.create(this.username, this.password, this.role, uuid.toString());
			
			return this.role;	
		} else {
			System.out.print("Registration is not longer available.");
			return "fail";
		}
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

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public boolean isRegistrationAvailable() {
		return registrationAvailable;
	}

	public void setRegistrationAvailable(boolean registrationAvailable) {
		this.registrationAvailable = registrationAvailable;
	}
}
