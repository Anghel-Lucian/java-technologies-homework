package com.document.config;

import java.util.Set;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

//import com.document.filters.ViewUserCacheFilter;
import com.document.services.AddUserService;
import com.document.services.DeleteUserService;
import com.document.services.UpdateUserService;
import com.document.services.ViewUserService;

@ApplicationPath("rest")
@ApplicationScoped
public class ApplicationConfig extends Application {

	private void addRestResourceClasses(Set<Class<?>> resources) {
		resources.add(AddUserService.class);
		resources.add(DeleteUserService.class);
		resources.add(UpdateUserService.class);
		resources.add(ViewUserService.class);
//		resources.add(ViewUserCacheFilter.class);
	}
	
	public Set<Class<?>> getClasses() {
		Set<Class<?>> resources = new java.util.HashSet<>();
		addRestResourceClasses(resources);
		return resources;
	}
}
