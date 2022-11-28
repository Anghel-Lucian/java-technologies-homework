package com.document.repository;

import java.util.Date;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import com.document.entity.Settings;

@Stateless
public class SettingsRepository {

	@PersistenceContext(unitName="lab07PU")
	EntityManager em;
	
	public Settings findWithCriteria() {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Settings> query = builder.createQuery(Settings.class);
		Root<Settings> e = query.from(Settings.class);
		Predicate condition = builder.equal(e.get("id"), "setting");
		
		query.where(condition);
		
		TypedQuery<Settings> q = em.createQuery(query);
		
		return q.getSingleResult();
	}
	
	@Transactional
	public void updateRegistrationDates(Date registrationStart, Date registrationEnd) {
		Settings settings = em.find(Settings.class, "setting");
		
		settings.setRegistrationStart(registrationStart);
		settings.setRegistrationEnd(registrationEnd);
	}
	
	public boolean registrationIsAvailable() {
		Settings settings = this.findWithCriteria();
		Date now = new Date();
		Date registrationStart = null;
		Date registrationEnd = null;

		registrationStart = settings.getRegistrationStart();
		registrationEnd = settings.getRegistrationEnd();
		
		System.out.print(now);
		System.out.print(registrationStart);
		System.out.print(registrationEnd);
		System.out.print(now.after(registrationStart) && now.before(registrationEnd));
		
		return now.after(registrationStart) && now.before(registrationEnd);
	}
	
	@Transactional
	public void updateSubmissionDates(Date submissionStart, Date submissionEnd) {
		Settings settings = em.find(Settings.class, "setting");
		
		settings.setSubmissionStart(submissionStart);
		settings.setSubmissionEnd(submissionEnd);
	}
	
	public boolean submissionIsAvailable() {
		Settings settings = this.findWithCriteria();
		Date now = new Date();
		Date submissionStart = null;
		Date submissionEnd = null;
		
		submissionStart = settings.getSubmissionStart();
		submissionEnd = settings.getSubmissionEnd();
		
		return now.after(submissionStart) && now.before(submissionEnd);
	}
}
