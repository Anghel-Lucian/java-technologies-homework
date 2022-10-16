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

// validates if a City already exists or not
// error message will be displayed under addCities form
@FacesValidator("cityValidator")
public class CityValidator implements Validator<Object> {

	@Override
	public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
		UIInput nameHidden = (UIInput) component.getAttributes().get("nameHidden");
		
		Object name = nameHidden.getSubmittedValue();

		DBManager dbm = new DBManager();
		boolean cityExists = false;
		         	 
		try {
			cityExists = dbm.cityExists(name.toString());
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
		if(cityExists) {
			FacesMessage msg = new FacesMessage("City already exist.","Another city with name " + name + " exists.");
		    msg.setSeverity(FacesMessage.SEVERITY_ERROR);
		    throw new ValidatorException(msg);	 
		}
	}

}
