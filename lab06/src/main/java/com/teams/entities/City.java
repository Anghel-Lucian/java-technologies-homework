package com.teams.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name="city")
@NamedQueries({
	@NamedQuery(name = "City.findAll", query = "SELECT c FROM City c")
})
public class City implements Serializable {
	
	@Id
	@Column(name="city_id")
	private String id;
	
	@Column(name="city_name")
	private String name;
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getId() {
		return id;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	// needed by the Converter to accurately pick the selected City from the TeamBean class
	@Override
	public boolean equals(Object obj) {
	    if (this == obj)
	        return true;
	    if (obj == null)
	        return false;
	    if (!(obj instanceof City))
	        return false;
	    City other = (City) obj;
	    if (this.id == null) {
	        if (other.getId() != null)
	            return false;
	    } else if (!this.id.equals(other.getId()))
	        return false;
	    return true;
	}
}
