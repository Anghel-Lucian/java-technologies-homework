package com.document.repository;

import java.io.IOException;
import java.util.ArrayList;

import javax.ejb.Stateless;
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.document.entity.Document;
import com.document.interceptors.LoggerInterceptor;

@Stateless
public class DocumentRepository {

	@PersistenceContext(unitName="lab07PU")
	EntityManager em;
	
	public ArrayList<Document> findAll() {
		@SuppressWarnings("unchecked")
		ArrayList<Document> documents = new ArrayList<Document>(em.createQuery("SELECT d FROM Document d").getResultList());
	
		return documents;
	}
	
	public Document findWithCriteria(String name, String authors) {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Document> query = builder.createQuery(Document.class);
		Root<Document> e = query.from(Document.class);
		ArrayList<Predicate> predicates = new ArrayList<Predicate>();
		Predicate finalCondition = null;
		
		if(!name.isEmpty()) {
			Predicate condition = builder.equal(e.get("name"), name);
			predicates.add(condition);
		}
		
		if(!authors.isEmpty()) {
			Predicate condition = builder.equal(e.get("authors"), authors);
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
		
		TypedQuery<Document> q = em.createQuery(query);
		
		return q.getSingleResult();
	}
	
	@Interceptors(LoggerInterceptor.class)
	public void create(String name, String authors, String id) throws IOException {
		Document document = new Document();
		
		document.setName(name);
		document.setAuthors(authors);
		document.setId(id);
		
		em.persist(document);
	}
}
