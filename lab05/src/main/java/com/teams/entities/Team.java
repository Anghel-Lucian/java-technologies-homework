package com.teams.entities;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name="team")
@NamedQueries({
	@NamedQuery(name = "Team.findAll", query = "SELECT t FROM Team t")
})
public class Team implements Serializable {
	
	@Id
	@Column(name="team_id")
	private String id;
	
	@Column(name="team_name")
	private String name;
	
	@Column(name="team_founding_date")
	private String foundingDate;
	
	@Column(name="team_city")
	private String city;
	
	@Column(name="team_city_name")
	private String cityName;
	
	@JoinColumn(name="team_players")
	@OneToMany(mappedBy="team")
	private Set<Player> players;
	
	public void setId(String id) {
		this.id = id;
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
