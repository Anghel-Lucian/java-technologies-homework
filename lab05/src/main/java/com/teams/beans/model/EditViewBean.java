package com.teams.beans.model;

import java.io.Serializable;
import java.sql.SQLException;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import com.teams.entities.City;
import com.teams.utils.DBManager;
import com.teams.utils.DataTableColumn;

@Named("edit")
@ViewScoped
public class EditViewBean implements Serializable {
	private static final long serialVersionUID = 1L;
	private Object selected;
	private DataTableColumn[] fields;
	private String name;
	private String id;

	public void doEdit() throws ClassNotFoundException, SQLException {
		DBManager dbm = new DBManager();
		City city = new City();
		city.setId(this.id);
		city.setName(this.name);
		dbm.updateCity(city);
	}
	
	public Object getSelected() {
		return selected;
	}

	public void setSelected(Object selected) throws ClassNotFoundException, SQLException {		
		City city = (City)selected;
		this.setFields(new CityBean().getColumns());
		
		this.setId(city.getId());
		this.setName(city.getName());
		
		System.out.print(city.getId());
	}
	
	public DataTableColumn[] getFields() {
		return fields;
	}

	public void setFields(DataTableColumn[] fields) {
		this.fields = fields;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
