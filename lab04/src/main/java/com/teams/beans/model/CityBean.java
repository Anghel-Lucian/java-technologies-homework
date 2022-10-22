package com.teams.beans.model;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.UUID;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import com.teams.entities.City;
import com.teams.utils.DBManager;
import com.teams.utils.DataTableColumn;

// class used in addCities page. cities field is used to render a table of cities;
// also responsible with adding new cities to the DB
@Named("city")
@ViewScoped
public class CityBean implements Serializable {
	private static final long serialVersionUID = 1L;
	private DataTableColumn[] columns = {
			new DataTableColumn("name", "Name")
		};
	private ArrayList<City> cities = new ArrayList<City>();
	private String name;
	
	public CityBean() throws ClassNotFoundException, SQLException {
		DBManager dbm = new DBManager();
		ArrayList<City> cities = dbm.getCities();

		this.cities = cities;
	}
	
	public ArrayList<City> getCities() {
		return this.cities;
	}
	
	public void addCity() throws ClassNotFoundException, SQLException {
		String id = UUID.randomUUID().toString();
		City city = new City(id, this.name);
		DBManager dbm = new DBManager();
		
		dbm.addCity(city);
		this.cities.add(city);
		
		this.setName("");
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public DataTableColumn[] getColumns() {
		return columns;
	}

	public void setColumns(DataTableColumn[] columns) {
		this.columns = columns;
	};
}
