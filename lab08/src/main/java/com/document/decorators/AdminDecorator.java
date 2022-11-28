package com.document.decorators;

import java.io.Serializable;
import java.util.Date;

import javax.decorator.Decorator;
import javax.decorator.Delegate;
import javax.enterprise.inject.Any;
import javax.inject.Inject;

import com.document.interfaces.Admin;
import com.document.view.AdminBean;

@SuppressWarnings("serial")
@Decorator
public abstract class AdminDecorator implements Serializable, Admin {
	@Inject @Delegate @Any
	AdminBean admin;
	
	public void updateRegistrationDates() {
		System.out.print("[STARTED DECORATION BY]: AdminDecorator");

		Date registrationStart = admin.getRegistrationStart();
		Date registrationEnd = admin.getRegistrationEnd();
		
		if(registrationStart.after(registrationEnd)) {
			System.out.print("[DECORATOR AdminDecorator]: registrationStart cannot be after registrationEnd.");
		} else {
			admin.updateRegistrationDates();
		}
		
		System.out.print("[DECORATED BY]: AdminDecorator");
	}
	
	public void updateSubmissionDates() {
		System.out.print("[STARTED DECORATION BY]: AdminDecorator");

		Date submissionStart = admin.getSubmissionStart();
		Date submissionEnd = admin.getSubmissionEnd();
		
		if(submissionStart.after(submissionEnd)) {
			System.out.print("[DECORATOR AdminDecorator]: submissionStart cannot be after submissionEnd.");
		} else {
			admin.updateSubmissionDates();
		}
		
		System.out.print("[DECORATED BY]: AdminDecorator");
	}
}