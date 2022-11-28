package com.document.repository;

import java.util.ArrayList;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.enterprise.event.Observes;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import com.document.bean.CurrentUserService;
import com.document.entity.Document;
import com.document.entity.User;
import com.document.entity.UserAdmin;
import com.document.entity.UserAuthor;
import com.document.entity.UserReviewer;

@Stateless
public class UserRepository {

	@EJB
	CurrentUserService cus;
	
	@PersistenceContext(unitName="lab07PU")
	EntityManager em;
	
	public boolean userExists(String username, String password) {
		return this.findWithCriteria(username, password) != null;
	}
	
	public ArrayList<User> findAll() {
		@SuppressWarnings("unchecked")
		ArrayList<User> users = new ArrayList<User>(em.createQuery("SELECT u FROM User u").getResultList());
		
		return users;
	}
	
	public User findWithCriteria(String username, String password) {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<User> query = builder.createQuery(User.class);
		Root<User> e = query.from(User.class);
		ArrayList<Predicate> predicates = new ArrayList<Predicate>();
		Predicate finalCondition = null;
		
		if(!username.isEmpty()) {
			Predicate condition = builder.equal(e.get("username"), username);
			predicates.add(condition);
		}
		
		if(!password.isEmpty()) {
			Predicate condition = builder.equal(e.get("password"), password);
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
		
		TypedQuery<User> q = em.createQuery(query);
		
		return q.getSingleResult();
	}
	
	public User findById(String id) {
		User user = (User) em.createQuery("SELECT u FROM User u WHERE u.id = :id").setParameter("id", id).getSingleResult();
		
		return user;
	}
	
	public void create(String username, String password, String role, String id) {
		if(role.equals("admin")) {
			this.createUserAdmin(username, password, id);
		} else if(role.equals("author")) {
			this.createUserAuthor(username, password, id);
		} else if(role.equals("reviewer")) {
			this.createUserReviewer(username, password, id);
		}
	}
	
	private void createUserAdmin(String username, String password, String id) {
		UserAdmin user = new UserAdmin();
		
		user.setUsername(username);
		user.setPassword(password);
		user.setId(id);
		
		em.persist(user);
	}
	
	private void createUserAuthor(String username, String password, String id) {
		UserAuthor user = new UserAuthor();
		
		user.setUsername(username);
		user.setPassword(password);
		user.setId(id);
		
		em.persist(user);
	}
	
	private void createUserReviewer(String username, String password, String id) {
		UserReviewer user = new UserReviewer();
		
		user.setUsername(username);
		user.setPassword(password);
		user.setId(id);
		
		em.persist(user);
	}
	
	@Transactional
	public void deleteUser(String id) {
		Query query = em.createQuery("DELETE FROM User u WHERE u.id = :id");
		query.setParameter("id", id).executeUpdate();
	}
	
	@Transactional
	public void onDocumentUpload(@Observes Document document) {
		System.out.print("[OBSERVER UserRepository]");
		
		String id = cus.getCurrentUser().getId();
		UserAuthor user = em.find(UserAuthor.class, id);
		
		user.setPublications(user.getPublications() + "," + document.getName());
	}
	
	@Transactional
	public void updateUsername(String id, String username) {
		User user = em.find(User.class, id);
		
		user.setUsername(username);
	}
	
	@Transactional
	public void updatePassword(String id, String password) {
		User user = em.find(User.class, id);
		
		user.setPassword(password);
	}
}
