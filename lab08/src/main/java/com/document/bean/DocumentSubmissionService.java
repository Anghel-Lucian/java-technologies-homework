package com.document.bean;

import java.util.UUID;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;

@RequestScoped
public class DocumentSubmissionService {
	
	@Produces
	public UUID getUuid() {
		return UUID.randomUUID();
	}
	
}
