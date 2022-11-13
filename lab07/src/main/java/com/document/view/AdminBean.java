package com.document.view;

import java.io.Serializable;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.document.bean.CurrentUserService;
import com.document.entity.Settings;
import com.document.entity.UserAdmin;
import com.document.interfaces.Admin;
import com.document.repository.SettingsRepository;

@SuppressWarnings("serial")
@Named("admin")
@ViewScoped
public class AdminBean implements Serializable, Admin {
	
	@Inject
	SettingsRepository sr;
	
	@EJB
	CurrentUserService cus;
	
	private UserAdmin admin;
	private Date registrationStart;
	private Date registrationEnd;
	private Date submissionStart;
	private Date submissionEnd;

	@PostConstruct
	private void init() {
		Settings settings = sr.findWithCriteria();
		
		this.setRegistrationStart(settings.getRegistrationStart());
		this.setRegistrationEnd(settings.getRegistrationEnd());
		
		this.setSubmissionStart(settings.getSubmissionStart());
		this.setSubmissionEnd(settings.getSubmissionEnd());
		
		this.setAdmin((UserAdmin) cus.getCurrentUser());
	}
	
	public void updateRegistrationDates() {
		sr.updateRegistrationDates(this.registrationStart, this.registrationEnd);
	}
	
	public void updateSubmissionDates() {
		sr.updateSubmissionDates(this.submissionStart, this.submissionEnd);
	}
	
	public UserAdmin getAdmin() {
		return admin;
	}

	public void setAdmin(UserAdmin admin) {
		this.admin = admin;
	}

	public Date getRegistrationStart() {
		return registrationStart;
	}

	public void setRegistrationStart(Date registrationStart) {
		this.registrationStart = registrationStart;
	}

	public Date getRegistrationEnd() {
		return registrationEnd;
	}

	public void setRegistrationEnd(Date registrationEnd) {
		this.registrationEnd = registrationEnd;
	}

	public Date getSubmissionStart() {
		return submissionStart;
	}

	public void setSubmissionStart(Date submissionStart) {
		this.submissionStart = submissionStart;
	}

	public Date getSubmissionEnd() {
		return submissionEnd;
	}

	public void setSubmissionEnd(Date submissionEnd) {
		this.submissionEnd = submissionEnd;
	}
	
	@SuppressWarnings("deprecation")
	public String getParsedDate(Date date) {
		int month = Integer.valueOf(date.getMonth()) + 1;
		return date.getYear() + 1900 + "-" + month + "-" + date.getDate();
	}
}
