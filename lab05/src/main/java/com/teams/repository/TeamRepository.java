package com.teams.repository;

import java.io.Serializable;
import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

import com.teams.entities.Team;

@SuppressWarnings("serial")
public class TeamRepository implements Serializable {
	
	// @PersistenceContext(unitName = "lab05PU") <= when moving to JTA transaction type
	// EntityManager em;
	@PersistenceUnit
	EntityManagerFactory emf;
	
	// READ
	public ArrayList<Team> findAllTeams() {
		EntityManager em = emf.createEntityManager();
		@SuppressWarnings("unchecked")
		ArrayList<Team> teams = new ArrayList<Team>(em.createNamedQuery("Team.findAll").getResultList());
	
		return teams;
	}
	
	// CREATE
	public void insertTeam(Team team) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		
		// Save city to DB
		em.persist(team);
		
		em.getTransaction().commit();
	}
	
	// TODO: transform team into entity
	// TODO: create player entity
	// TODO: use JPA criteria API to create search for players
}
