package com.document.view;

import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.event.Event;
import javax.enterprise.inject.Any;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.Part;

import com.document.bean.CurrentUserService;
import com.document.entity.Document;
import com.document.entity.User;
import com.document.repository.DocumentRepository;

@Named("document")
@RequestScoped
public class DocumentBean {
	
	private ArrayList<Document> documents;
	private String additionalAuthors;
	private Part document;
	
	@Inject
	DocumentRepository dr;
	
	@Inject
	UUID uuid;
	
	@EJB
	CurrentUserService cus;
	
	@Inject @Any
	Event<Document> event;
	
	@PostConstruct
	public void init() {
		User currentUser = cus.getCurrentUser();
		ArrayList<Document> documents = dr.findAll();
		ArrayList<Document> documentsUserSpecific = new ArrayList<Document>();
		
		if(currentUser != null && !currentUser.getUsername().equals("admin")) {
			for(int i = 0; i < documents.size(); i++) {
				if(documents.get(i).getAuthors().contains(currentUser.getUsername())) {
					documentsUserSpecific.add(documents.get(i));
				}
			}
			
			this.setDocuments(documentsUserSpecific);
			return;
		}
		
		this.setDocuments(documents);
	}

	public void upload() throws IOException {
		String fileName = getFileName(document);
		String username = cus.getCurrentUser().getUsername();
		String authors = username + "," + additionalAuthors;
		Document uploadedDocument = new Document();
		uploadedDocument.setAuthors(authors);
		uploadedDocument.setName(fileName);
		
		document.write(fileName);

		dr.create(fileName, authors, uuid.toString());
		this.documents.add(uploadedDocument);
		
		// This event will be intercepted in UserRepository, and will result a user entity being updated with the newly added document
		event.fire(uploadedDocument);
	}
	
	public static String getFileName(Part part) {
		for(String cd : part.getHeader("content-disposition").split(";")) {
			if(cd.trim().startsWith("filename")) {
				String filename = cd.substring(cd.indexOf("=") + 1).trim().replace("\"", "");
				return filename.substring(filename.lastIndexOf("/") + 1).substring(filename.lastIndexOf('\\') + 1);
			}
		}
		
		return null;
	}
	
	public ArrayList<Document> getDocuments() {
		return documents;
	}

	public void setDocuments(ArrayList<Document> documents) {
		this.documents = documents;
	}
	
	public Part getDocument() {
		return document;
	}

	public void setDocument(Part document) {
		this.document = document;
	}

	public String getAdditionalAuthors() {
		return additionalAuthors;
	}

	public void setAdditionalAuthors(String additionalAuthors) {
		this.additionalAuthors = additionalAuthors;
	}
}
