package com.teams.repository;

import java.io.Serializable;
import java.util.ArrayList;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import com.teams.entities.City;

@SuppressWarnings("serial")
@Stateless
public class CityRepository implements Serializable {
	
	 @PersistenceContext(unitName = "lab06PU")
	 EntityManager em;
	
	// READ
	public ArrayList<City> findAllCities() {
		@SuppressWarnings("unchecked")
		ArrayList<City> cities = new ArrayList<City>(em.createNamedQuery("City.findAll").getResultList());
	
		return cities;
	}
	
	public City findCity(String id) {
		return em.find(City.class, id);
	}
	
	// CREATE
	@Transactional
	public void insertCity(City city) {
		em.persist(city);
	}
	
	// UPDATE
	@Transactional
	public void updateCity(City city) {
		City foundCity = em.find(City.class, city.getId());
		
		foundCity.setName(city.getName());
	}
	
	// DELETE
	@Transactional
	public void deleteCity(String id) {
		City foundCity = em.find(City.class, id);
		
		em.remove(foundCity);
	}
}
