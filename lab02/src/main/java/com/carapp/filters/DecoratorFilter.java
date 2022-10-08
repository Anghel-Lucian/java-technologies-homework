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
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

@WebFilter(
		dispatcherTypes = {DispatcherType.REQUEST}, 
		urlPatterns = { 
				"/DecoratorFilter", 
				"/car"
		})
public class DecoratorFilter extends HttpFilter implements Filter {

	// create a new FilteredRequest class that will be passed down instead of the original request
	// ServletRequest class does not have a setParameter method, so this is the solution one would arrive at
	static class FilteredRequest extends HttpServletRequestWrapper {
		public FilteredRequest(ServletRequest request) {
			super((HttpServletRequest)request);
		}
		
		public String getParameter(String paramName) {
			String value = super.getParameter(paramName);
			
			if(!paramName.equals("make")) {
				return value;
			}
			
			return this.decorate(value);
		}
		
		private String decorate(String value) {
			// decorate the "make" value only if it is equal to "BMW"
			switch(value) {
				case "BMW":
					return "Bavaria Motor Werks";
				default:
					return value;
			}
		}
	}
	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		chain.doFilter(new FilteredRequest(request), response);
	}

	public void init(FilterConfig fConfig) throws ServletException {
		System.out.print("DECORATOR FILTER INITIALIZED\n");
	}
}
