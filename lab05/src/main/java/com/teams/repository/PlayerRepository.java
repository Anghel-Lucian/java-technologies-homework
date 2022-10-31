package com.teams.repository;

import java.util.ArrayList;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.teams.entities.Player;
import com.teams.entities.Team;

@Stateless
public class PlayerRepository {

	@PersistenceUnit
	EntityManagerFactory emf;
	
	public ArrayList<Player> findWithCriteria(String name, int minimumAge, int maximumAge, String team) {
		EntityManager em = emf.createEntityManager();
		
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Player> query = builder.createQuery(Player.class);
		Root<Player> e = query.from(Player.class);
		ArrayList<Predicate> predicates = new ArrayList<Predicate>();
		Predicate finalCondition = null;
		
		if(!name.isEmpty()) {
			Predicate condition = builder.equal(e.get("name"), name);
			predicates.add(condition);
		}
//		
//		if(!minimumAge.isEmpty()) {
//			Predicate condition = builder.equal(e.get("startingTime"), startingTime);
//			predicates.add(condition);
//		}
//		
//		if(duration > 0) {
//			Predicate condition = builder.equal(e.get("duration"), duration);
//			predicates.add(condition);
//		}
		
		for(int i = 0; i < predicates.size(); i++) {
			if(i == 0) {
				finalCondition = predicates.get(i);
				continue;
			}
			
			finalCondition = builder.and(finalCondition, predicates.get(i));
		}
		
		query.where(finalCondition);
		
		TypedQuery<Player> q = em.createQuery(query);
		
		return new ArrayList<Player>(q.getResultList());
	}
		
}
