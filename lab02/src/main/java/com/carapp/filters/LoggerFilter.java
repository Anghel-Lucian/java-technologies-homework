package com.carapp.filters;

import java.io.IOException;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;

@WebFilter(
		dispatcherTypes = {DispatcherType.REQUEST}, 
		urlPatterns = { 
				"/LoggerFilter", 
				"/car"
		})
public class LoggerFilter extends HttpFilter implements Filter {
 
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		String category = request.getParameter("category");
	    String make = request.getParameter("make");
	    int year = Integer.parseInt(request.getParameter("year"));
	    
	    // log request parameters
	    System.out.print("[LOGGER FILTER] Category: " + category + "\n");
	    System.out.print("[LOGGER FILTER] Make: " + make + "\n");
	    System.out.print("[LOGGAER FILTER] Year: " + year + "\n");
		
	    // dispatch the request and response further down the chain
		chain.doFilter(request, response);
	}

	public void init(FilterConfig fConfig) throws ServletException {
		System.out.print("LOGGER FILTER INITIALIZED\n");
	}
}
