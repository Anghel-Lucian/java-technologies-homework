package com.teams.repository;

import java.io.Serializable;
import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

import com.teams.entities.City;

@SuppressWarnings("serial")
public class CityRepository implements Serializable {
	
	// @PersistenceContext(unitName = "lab05PU") <= when moving to JTA transaction type
	// EntityManager em;
	@PersistenceUnit
	EntityManagerFactory emf;
	
	// READ
	public ArrayList<City> findAllCities() {
		EntityManager em = emf.createEntityManager();
		@SuppressWarnings("unchecked")
		ArrayList<City> cities = new ArrayList<City>(em.createNamedQuery("City.findAll").getResultList());
	
		return cities;
	}
	
	// CREATE
	public void insertCity(City city) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		
		// Save city to DB
		em.persist(city);
		
		em.getTransaction().commit();
	}
	
	// UPDATE
	public void updateCity(City city) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		
		City foundCity = em.find(City.class, city.getId());
		
		foundCity.setName(city.getName());
		
		em.getTransaction().commit();
	}
	
	// DELETE
	// TODO: delete method with JPA
	// TODO: transform team into entity
	// TODO: create player entity
	// TODO: use JPA criteria API to create search for players
}
