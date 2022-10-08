package com.carapp.listeners;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletRequestAttributeListener;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class CategoryListener implements ServletContextListener, ServletRequestListener, ServletRequestAttributeListener {
    
	// this should be called once per session
	public void contextInitialized(ServletContextEvent sce)  { 
    	ServletContext ctx = sce.getServletContext();
    	
    	String category = ctx.getInitParameter("defaultCategory");
    	
    	// save the "category" init param value in a context attribute
    	ctx.setAttribute("defaultCategory", category);
    	
    	System.out.print("CATEGORY LISTENER INITIALIZED\n");
    }
	
}
