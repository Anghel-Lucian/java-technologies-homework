package com.document.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Future;
import javax.validation.constraints.Past;

@SuppressWarnings("serial")
@Entity
@Table(name="settings")
public class Settings implements Serializable {
	
	@Id
	private String id;
	
	@Past
	@Column(name="registration_start")
	private Date registrationStart;
	
	@Future
	@Column(name="registration_end")
	private Date registrationEnd;
	
	@Past
	@Column(name="submission_start")
	private Date submissionStart;
	
	@Future
	@Column(name="submission_end")
	private Date submissionEnd;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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
}
