package com.teams.entities;

public class City {
	private String id;
	private String name;
	
	public City(String id, String name) {
		this.id = id;
		this.name = name;
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
