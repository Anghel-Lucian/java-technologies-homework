package com.teams.repository;

import java.io.Serializable;
import java.util.ArrayList;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import com.teams.entities.Team;

@SuppressWarnings("serial")
@Stateless
public class TeamRepository implements Serializable {
	
	 @PersistenceContext(unitName = "lab06PU")
	 EntityManager em;
	
	// READ
	public ArrayList<Team> findAllTeams() {
		@SuppressWarnings("unchecked")
		ArrayList<Team> teams = new ArrayList<Team>(em.createNamedQuery("Team.findAll").getResultList());
	
		return teams;
	}
	
	// CREATE
	@Transactional
	public void insertTeam(Team team) {
		// Save city to DB
		em.persist(team);
	}
}
