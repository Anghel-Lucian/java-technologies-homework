package com.teams.beans.model;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.teams.entities.City;
import com.teams.entities.Team;
import com.teams.repository.CityRepository;
import com.teams.repository.TeamRepository;
import com.teams.utils.DataTableColumn;

// class used in addTeams page. teams field is used to render a table of teams;
// also responsible with adding new teams to the DB
// cities field is used to display a selection box with all of the possible cities a team can be created in
// the city can be selected because of a POJO converter (CityConverter)
@Named("team")
@ViewScoped
public class TeamBean implements Serializable {
	private static final long serialVersionUID = 1L;
	private DataTableColumn[] columns = {
			new DataTableColumn("name", "Name"),
			new DataTableColumn("foundingDate", "Founding Date"),
			new DataTableColumn("cityName", "City")
		};
	private String name;
	private Date foundingDate;
	private String cityId;
	private ArrayList<Team> teams = new ArrayList<Team>();
	private ArrayList<City> cities = new ArrayList<City>();
	
	@Inject
	TeamRepository tp;
	
	@Inject
	CityRepository cp;
	
	@PostConstruct
	public void init() {
		ArrayList<City> cities = cp.findAllCities();
		ArrayList<Team> teams = tp.findAllTeams();
		
		this.setTeams(teams);
		this.setCities(cities);
	}
	
	public void addTeam() throws ClassNotFoundException, SQLException {
		String id = UUID.randomUUID().toString();
		String parsedFoundingDate = this.getParsedFoundingDate();
		
		Team team = new Team();
		
		team.setId(id);
		team.setName(this.name);
		team.setFoundingDate(parsedFoundingDate);
		
		City teamCity = cp.findCity(this.cityId);
		
		team.setCity(this.cityId);
		team.setCityName(teamCity.getName());
		
		tp.insertTeam(team);
		
		this.teams.add(team);
		this.name = "";
		this.cityId = "";
		this.foundingDate = null;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public Date getFoundingDate() {
		return foundingDate;
	}
	
	public void setFoundingDate(Date foundingDate) {
		this.foundingDate = foundingDate;
	}
	
	public ArrayList<City> getCities() {
		return this.cities;
	}

	public void setCities(ArrayList<City> cities) {
		this.cities = cities;
	}
	
	private void setTeams(ArrayList<Team> teams) {
		this.teams = teams;
	}

	public ArrayList<Team> getTeams() {
		return this.teams;
	}
	
	@SuppressWarnings("deprecation")
	public String getParsedFoundingDate() {
		int month = Integer.valueOf(this.foundingDate.getMonth()) + 1;
		return this.foundingDate.getYear() + 1900 + "-" + month + "-" + this.foundingDate.getDate();
	}

	public DataTableColumn[] getColumns() {
		return columns;
	}

	public void setColumns(DataTableColumn[] columns) {
		this.columns = columns;
	}

	public String getCityId() {
		return cityId;
	}

	public void setCityId(String cityId) {
		this.cityId = cityId;
	}
}
