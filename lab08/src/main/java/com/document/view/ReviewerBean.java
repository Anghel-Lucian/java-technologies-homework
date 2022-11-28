package com.document.view;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import com.document.bean.CurrentUserService;
import com.document.entity.UserReviewer;

@SuppressWarnings("serial")
@Named("reviewer")
@ViewScoped
public class ReviewerBean implements Serializable {
	
	@EJB
	CurrentUserService cus;
	
	private UserReviewer reviewer;

	@PostConstruct
	private void init() {
		this.setReviewer((UserReviewer) cus.getCurrentUser());
	}
	
	public UserReviewer getReviewer() {
		return reviewer;
	}

	public void setReviewer(UserReviewer reviewer) {
		this.reviewer = reviewer;
	}
	
}
