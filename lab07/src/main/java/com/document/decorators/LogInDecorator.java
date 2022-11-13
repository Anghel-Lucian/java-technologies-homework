package com.document.decorators;

import javax.decorator.Decorator;
import javax.decorator.Delegate;
import javax.enterprise.inject.Any;
import javax.inject.Inject;

import com.document.interfaces.LogIn;
import com.document.repository.UserRepository;
import com.document.view.LogInBean;

@Decorator
public abstract class LogInDecorator implements LogIn {
	@Inject @Delegate @Any
	LogInBean logIn;
	
	@Inject
	UserRepository ur;
	
	public String logIn () {
		System.out.print("[STARTED DECORATION BY]: LogInDecorator");
		boolean userExists = ur.userExists(logIn.getUsername(), logIn.getPassword());
				
		if(!userExists) return "fail";
		
		System.out.print("[DECORATED BY]: LogInDecorator");
		return logIn.logIn();
	}
}