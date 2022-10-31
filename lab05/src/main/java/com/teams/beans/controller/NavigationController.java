package com.teams.beans.controller;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

// class that controls web flow. Rules are in faces-config.
@Named("navigation")
@SessionScoped
public class NavigationController implements Serializable {
	
	private static final long serialVersionUID = 1L;

	public String teams() {
		return "addTeams";
	}
	
	public String cities() {
		return "addCities";
	}
	
	public String home() {
		return "home";
	}
	
	public String players() {
		return "players";
	}
}
