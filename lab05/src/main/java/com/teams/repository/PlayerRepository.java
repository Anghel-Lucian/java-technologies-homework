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

@Stateless
public class PlayerRepository {

	@PersistenceUnit
	EntityManagerFactory emf;
	
	public ArrayList<Player> findWithCriteria(String name, int minimumAge, int maximumAge) {
		EntityManager em = emf.createEntityManager();
		
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Player> query = builder.createQuery(Player.class);
		Root<Player> e = query.from(Player.class);
		ArrayList<Predicate> predicates = new ArrayList<Predicate>();
		Predicate finalCondition = null;
		
		if(!name.isEmpty()) {
			Predicate condition = builder.like(e.get("name"), '%' + name + '%'); // added % wildcard because otherwise the app would select no row from the DB. In that sense, the criteria by "name" will function as a .contains in the app
			predicates.add(condition);
		}
		
		if(minimumAge > 0) {
			Predicate condition = builder.greaterThanOrEqualTo(e.get("age"), minimumAge);
			predicates.add(condition);
		}
		
		if(maximumAge > 0 && maximumAge > minimumAge) {
			Predicate condition = builder.lessThanOrEqualTo(e.get("age"), maximumAge);
			predicates.add(condition);
		}
		
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
