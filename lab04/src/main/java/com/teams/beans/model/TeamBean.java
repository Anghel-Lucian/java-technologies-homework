package com.teams.beans.model;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import com.teams.entities.City;
import com.teams.entities.Team;
import com.teams.utils.DBManager;
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
	private City city;
	private ArrayList<Team> teams = new ArrayList<Team>();
	private ArrayList<City> cities = new ArrayList<City>();
	
	public TeamBean() throws ClassNotFoundException, SQLException {
		DBManager dbm = new DBManager();
		ArrayList<Team> teams = dbm.getTeams();
		ArrayList<City> cities = dbm.getCities();
		
		this.teams = teams;
		this.setCities(cities);
	}
	
	public ArrayList<Team> getTeams() {
		return this.teams;
	}
	
	public void addTeam() throws ClassNotFoundException, SQLException {
		String id = UUID.randomUUID().toString();
		String parsedFoundingDate = this.getParsedFoundingDate();
		
		Team team = new Team(id, this.name, parsedFoundingDate, this.city.getId(), this.city.getName());
		DBManager dbm = new DBManager();
		
		dbm.addTeam(team);
		this.teams.add(team);
		
		this.name = "";
		this.foundingDate = null;
		this.city = null;
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
	
	public City getCity() {
		return city;
	}
	
	public void setCity(City city) {
		this.city = city;
	}
	
	public ArrayList<City> getCities() {
		return cities;
	}

	public void setCities(ArrayList<City> cities) {
		this.cities = cities;
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
}
