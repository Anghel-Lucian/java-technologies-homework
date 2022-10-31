package com.teams.converters;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import com.teams.entities.City;
import com.teams.repository.CityRepository;

@FacesConverter(value = "cityConverter")
public class CityConverter implements Converter<Object> {
	@Inject
	CityRepository cp;
	
	@Override
	public City getAsObject(FacesContext facesContext, UIComponent component, String value) {
		if(value != null && value.trim().length() > 0) {
			try {
				City city = cp.findCity(value);
				return city;
			} catch(Exception e) {
				e.printStackTrace();
				String msgDetail = "Internal error when fetching city by ID. Could not find city.";
	            FacesMessage msg = new FacesMessage("Error", msgDetail);
	            
	            FacesContext.getCurrentInstance().addMessage(null, msg);
	            
	            throw new ConverterException(msg);
			}
		} else {
			return null;
		}
	}

	@Override
	public String getAsString(FacesContext facesContext, UIComponent component, Object value) {
		if(value != null) {
			return ((City) value).getId();
		} else {
			return null;
		}
	}

}