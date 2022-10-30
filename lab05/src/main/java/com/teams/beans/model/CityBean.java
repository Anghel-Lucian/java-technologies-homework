package com.teams.beans.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.UUID;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.teams.entities.City;
import com.teams.repository.CityRepository;
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
	private String updatedCityId;
	private String updatedCityName;
	
	@Inject
	CityRepository cp;
	
	public ArrayList<City> getCities() {
		return cp.findAllCities();
	}
	
	public void addCity() {
		String id = UUID.randomUUID().toString();
		City city = new City();
		
		city.setId(id);
		city.setName(this.name);
		
		// Save city in DB
		cp.insertCity(city);
		
		this.cities.add(city);
		this.setName("");
	}
	
	public void updateCity() {
		City city = new City();
		
		city.setId(this.updatedCityId);
		city.setName(this.updatedCityName);
		
		cp.updateCity(city);
		
		this.setUpdatedCityId("");
		this.setUpdatedCityName("");
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
	}

	public String getUpdatedCityId() {
		return updatedCityId;
	}

	public void setUpdatedCityId(String updatedCityId) {
		this.updatedCityId = updatedCityId;
	}

	public String getUpdatedCityName() {
		return updatedCityName;
	}

	public void setUpdatedCityName(String updatedCityName) {
		this.updatedCityName = updatedCityName;
	};
}
