package com.document.view;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.document.bean.CurrentUserService;
import com.document.entity.UserAuthor;
import com.document.repository.SettingsRepository;

@SuppressWarnings("serial")
@Named("author")
@ViewScoped
public class AuthorBean implements Serializable {
	
	@Inject
	SettingsRepository sr;
	
	@EJB
	CurrentUserService cus;
	
	private UserAuthor author;
	private boolean submissionAvailable;

	@PostConstruct
	private void init() {
		this.setAuthor((UserAuthor) cus.getCurrentUser());
		this.setSubmissionAvailable(sr.submissionIsAvailable());
	}
	
	public UserAuthor getAuthor() {
		return author;
	}

	public void setAuthor(UserAuthor author) {
		this.author = author;
	}

	public boolean isSubmissionAvailable() {
		return submissionAvailable;
	}

	public void setSubmissionAvailable(boolean submissionAvailable) {
		this.submissionAvailable = submissionAvailable;
	}
	
}
