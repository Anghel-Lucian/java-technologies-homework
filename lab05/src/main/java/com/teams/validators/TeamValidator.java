package com.teams.validators;
import java.sql.SQLException;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import com.teams.utils.DBManager;

// validates if a Team already exists or not
// error message will be displayed under addTeams form
@FacesValidator("teamValidator")
public class TeamValidator implements Validator<Object> {

	@Override
	public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
		UIInput nameHidden = (UIInput) component.getAttributes().get("nameHidden");
		UIInput foundingDateHidden = (UIInput) component.getAttributes().get("foundingDateHidden");
		UIInput cityHidden = (UIInput) component.getAttributes().get("cityHidden");
		
		Object name = nameHidden.getSubmittedValue();
		Object foundingDate = foundingDateHidden.getSubmittedValue();
		Object city = cityHidden.getSubmittedValue();

		DBManager dbm = new DBManager();
		boolean teamExists = false;
		         	 
		try {
			teamExists = dbm.teamExists(name.toString(), foundingDate.toString(), city.toString());
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
		if(teamExists) {
			FacesMessage msg = new FacesMessage("Team already exists.","Another team " + name + ", " + foundingDate + " exists.");
		    msg.setSeverity(FacesMessage.SEVERITY_ERROR);
		    throw new ValidatorException(msg);	 
		}
	}

}
