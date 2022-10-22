package com.teams.entities;

public class Team {
	private String id;
	private String name;
	private String foundingDate;
	private String city;
	private String cityName;
	
	public Team(String id, String name, String foundingDate, String city, String cityName) {
		this.id = id;
		this.name = name;
		this.foundingDate = foundingDate;
		this.city = city;
		this.cityName = cityName;
	}
	
	public String getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getFoundingDate() {
		return foundingDate;
	}
	
	public void setFoundingDate(String foundingDate) {
		this.foundingDate = foundingDate;
	}
	
	public String getCity() {
		return city;
	}
	
	public void setCity(String city) {
		this.city = city;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
}
